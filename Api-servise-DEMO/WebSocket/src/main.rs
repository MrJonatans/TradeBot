
use tokio::net::TcpStream;
use tokio::signal;
use tokio_tungstenite::{client_async, tungstenite::protocol::Message};
use tokio_native_tls::native_tls::TlsConnector;
use tokio_native_tls::TlsConnector as TokioTlsConnector;
use url::Url;
use serde_json::json;
use futures_util::{StreamExt, SinkExt};

#[tokio::main]
async fn main() {
    // URL WebSocket API ByBit
    let url = Url::parse("wss://stream.bybit.com/v5/public/spot").unwrap();

    // Создаем конфигурацию TLS
    let tls_connector = TlsConnector::new().unwrap();
    let tls_connector = TokioTlsConnector::from(tls_connector);

    // Создаем TCP-соединение
    let domain = url.host_str().expect("No host in URL");
    let tcp_stream = TcpStream::connect((domain, 443)).await.expect("Failed to connect to host");

    // Оборачиваем TCP-соединение в TLS
    let tls_stream = tls_connector.connect(domain, tcp_stream).await.expect("TLS connection failed");

    // Устанавливаем защищенное WebSocket-соединение поверх TLS
    let (ws_stream, _response) = client_async(url.as_str(), tls_stream)
        .await
        .expect("Failed to connect with TLS");

    println!("Connected to ByBit WebSocket API with TLS");

    // Разделяем WebSocket на отправителя и получателя
    let (mut write, mut read) = ws_stream.split();

    // Подписываемся на обновления по торговой паре BTCUSD
    let subscribe_message = json!({
        "op": "subscribe",
        "args": ["publicTrade.BTCUSDT"]
    });
    write.send(Message::Text(subscribe_message.to_string()))
        .await
        .expect("Failed to send subscribe message");

    println!("Subscribed to BTCUSD trade updates");

    // Запускаем задачу обработки WebSocket сообщений
    let ws_task = tokio::spawn(async move {
        while let Some(msg) = read.next().await {
            match msg {
                Ok(Message::Text(text)) => {
                    println!("Received message: {}", text);
                },
                Ok(Message::Ping(ping)) => {
                    // Ответ на Ping сообщение
                    write.send(Message::Pong(ping)).await.expect("Failed to send Pong");
                },
                Err(e) => {
                    eprintln!("Error: {}", e);
                    break;
                },
                _ => {}
            }
        }
    });

    // Ожидаем сигнала Ctrl+C для завершения работы
    tokio::select! {
        _ = ws_task => {
            println!("WebSocket task completed");
        }
        _ = signal::ctrl_c() => {
            println!("Received Ctrl+C, shutting down");
        }
    }
}
