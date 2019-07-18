CREATE TABLE parkinglot(
    id varchar not null,
    name varchar(20) not null unique,
    capacity int not null,
    location varchar(50) not null,
    primary key(id)
);