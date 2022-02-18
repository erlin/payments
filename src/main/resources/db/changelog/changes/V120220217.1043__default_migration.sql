CREATE TABLE credit
(
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    createdAt               DATE DEFAULT CURRENT_DATE,
    amount                  DOUBLE,
    terms                   INTEGER,
    rate                    DOUBLE
);

CREATE TABLE payment
(
    id                      BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    payment_number          INTEGER,
    amount                  DOUBLE,
    payment_date            DATE NOT NULL,
    credit_id               BIGINT NOT NULL,
    FOREIGN KEY (credit_id)
        REFERENCES credit (id)
);