use std::{thread,time::Duration};
use reqwest::Error;
use serde::Deserialize;

#[derive(Deserialize)]
struct Ticker {
    symbol: String,
    lastPrice: String,
    indexPrice: String,
    markPrice: String,
    prevPrice24h: String,
    price24hPcnt: String,
    highPrice24h: String,
    lowPrice24h: String,
    openInterest: String,
    turnover24h: String,
    volume24h: String,
    fundingRate: String,
    nextFundingTime: String,
    // Добавьте другие поля по желанию
}

#[derive(Deserialize)]
struct ApiResponse {
    retCode: i32,
    retMsg: String,
    result: ResultData,
    // Можно добавить другие поля по желанию
}

#[derive(Deserialize)]
struct ResultData {
    category: String,
    list: Vec<Ticker>,
}

#[tokio::main]
async fn main() -> Result<(), Error> {
    let url: &str = "https://api.bybit.com/v5/market/tickers";

    for _ in 1..20{
        let response = take_respon(url, "BTCUSDT").await?;

        for ticker in response.result.list {
            println!("Symbol: {}, Last Price: {}, High Price: {}, Low Price: {}, Volume 24h: {}",
                     ticker.symbol, ticker.lastPrice, ticker.highPrice24h, ticker.lowPrice24h, ticker.volume24h);
        }
        thread::sleep(Duration::from_secs(2));
    }

    Ok(())
}


async fn take_respon(url: &str, tick:&str) -> Result<ApiResponse, Error> {
    let response: ApiResponse = reqwest::get(format!("{}?category=inverse&symbol={}", url, tick)).await?.json().await?;
    Ok(response)
}