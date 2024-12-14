CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password TEXT         NOT NULL,
    role     VARCHAR(10)  NOT NULL
);