
CREATE DATABASE testdb;

\connect postgres;

CREATE EXTENSION IF NOT EXISTS pg_stat_statements;
-- For insert simulation
-- CREATE EXTENSION IF NOT EXISTS pg_cron;

SELECT pg_reload_conf();

\connect testdb;

CREATE TABLE Trades(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2),
    volume FLOAT,
    trade TIMESTAMP
);
