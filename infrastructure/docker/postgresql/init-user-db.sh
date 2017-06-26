#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE todo_cqrs;
    GRANT ALL PRIVILEGES ON DATABASE todo_cqrs TO todocqrs_user;
EOSQL
