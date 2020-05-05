\c bank bankuser

DROP DATABASE IF EXISTS testbank;

CREATE DATABASE testbank;

-- switching to testbank
\c testbank bankuser;

CREATE TABLE users
(
    cpr  INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE banks
(
    cvr  INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE accounts
(
    id          SERIAL PRIMARY KEY,
    balance     INT,
    customerCpr INT references users (cpr) NOT NULL,
    bankCvr     INT references banks (cvr) NOT NULL
);

CREATE TABLE transactions
(
    id        SERIAL PRIMARY KEY,
    retriever INT references accounts (id) NOT NULL,
    giver     INT references accounts (id) NOT NULL,
    amount    INT                          NOT NULL
);
