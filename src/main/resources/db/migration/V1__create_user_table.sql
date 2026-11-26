create table users
(
    id bigint auto_increment primary key ,
    name varchar(100) not null ,
    email varchar(255) not null unique ,
    age int not null
);