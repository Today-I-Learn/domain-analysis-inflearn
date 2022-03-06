DROP TABLE IF EXISTS member;

create table member
(
    id       bigint auto_increment,
    email    varchar(255),
    password varchar(255),
    role     varchar(255),
    primary key (id)
)
