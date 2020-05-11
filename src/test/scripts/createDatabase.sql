    drop database if exists bank;
    create database bank;
    \c bank;

    create table customers (
        cpr VARCHAR(10) PRIMARY KEY,
        name VARCHAR(100) NOT NULL
    );

    CREATE TABLE banks (
        cvr VARCHAR(25) PRIMARY KEY,
        name VARCHAR(100) NOT NULL
    );

    CREATE TABLE accounts (
        id SERIAL PRIMARY KEY,
        balance INT DEFAULT 0,
        customerCpr VARCHAR(10) references customers (cpr) NOT NULL,
        bankCvr VARCHAR(25) references banks (cvr) NOT NULL
    );

    CREATE TABLE transactions(
        id SERIAL PRIMARY KEY,
        retriever INT references accounts (id) NOT NULL,
        giver     INT references accounts (id) NOT NULL,
        timestamp timestamp default current_timestamp,
        amount    INT NOT NULL
    );

    INSERT INTO customers(cpr, name)
    VALUES
        ('2102152020', 'Jonas'),
        ('3105071331', 'Alex'),
        ('1001882543', 'Christian'),
        ('3112201654', 'Kristoffer');

    INSERT INTO banks(cvr, name)
    VALUES
           ('DK-CVR-36729929', 'Gold bank');

    INSERT INTO accounts (balance, customerCpr, bankCvr)
    VALUES
           (1000, '2102152020', 'DK-CVR-36729929'),
           (2500, '2102152020', 'DK-CVR-36729929'),
           (0,    '2102152020', 'DK-CVR-36729929'),
           (12500,'2102152020', 'DK-CVR-36729929'),
           (3000, '3105071331', 'DK-CVR-36729929'),
           (9000, '3105071331', 'DK-CVR-36729929'),
           (15000,'3105071331', 'DK-CVR-36729929'),
           (200,  '3105071331', 'DK-CVR-36729929'),
           (500,  '3105071331', 'DK-CVR-36729929'),
           (3000, '1001882543', 'DK-CVR-36729929'),
           (9000, '1001882543', 'DK-CVR-36729929'),
           (1250, '1001882543', 'DK-CVR-36729929'),
           (10000,'1001882543', 'DK-CVR-36729929'),
           (9000, '1001882543', 'DK-CVR-36729929'),
           (250,  '1001882543', 'DK-CVR-36729929'),
           (15000,'3112201654', 'DK-CVR-36729929'),
           (0,    '3112201654', 'DK-CVR-36729929'),
           (2500, '3112201654', 'DK-CVR-36729929'),
           (250,  '3112201654', 'DK-CVR-36729929'),
           (5000, '3112201654', 'DK-CVR-36729929');

INSERT INTO transactions (retriever, giver, amount)
VALUES
    (1, 5, 1500),
    (3, 2, 2200),
    (9, 2, 1700),
    (4, 2, 300),
    (3, 1, 50),
    (3, 1, 100),
    (12, 6, 1500),
    (10, 4, 700),
    (13, 20, 200),
    (18, 16, 5000),
    (17, 20, 300),
    (15, 1, 100),
    (3, 14, 20),
    (2, 3, 35);
