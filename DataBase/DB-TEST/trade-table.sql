CREATE TABLE(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2),
    volume FLOAT,
    trade INTEGER,
);
