-- liquibase formatted sql

-- changeset mleushkin:1
CREATE TABLE users(
    id SERIAL,
    email TEXT
)

