# Dockerfile
FROM postgres:latest

# Установка необходимых пакетов
RUN apt-get update && apt-get install -y \
    git \
    make \
    gcc \
    postgresql-server-dev-all \
    && rm -rf /var/lib/apt/lists/*

# Установка pg_cron
RUN git clone --single-branch --branch master https://github.com/citusdata/pg_cron.git \
    && cd pg_cron \
    && make && make install \
    && cd .. && rm -rf pg_cron

# Включаем расширение
CMD ["postgres", "-c", "shared_preload_libraries=pg_cron"]
