DROP TABLE IF EXISTS `Member`;

CREATE TABLE Member
(
    id BIGINT PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);