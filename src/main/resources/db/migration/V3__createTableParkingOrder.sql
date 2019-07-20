CREATE TABLE parking_order(
    order_id varchar not null,
    parking_lot_name varchar(20) not null ,
    car_num varchar(20) not null,
    start_time varchar(50) not null,
    end_time varchar(50),
    status varchar(5) not null DEFAULT 'ON',
    primary key(order_id)
);