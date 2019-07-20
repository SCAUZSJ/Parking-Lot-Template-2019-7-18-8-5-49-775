CREATE TABLE parking_order(
    order_id varchar not null,
    parking_lot_name varchar(20) not null ,
    car_num varchar(20) not null,
    start_time timestamp not null DEFAULT now(),
    end_time timestamp,
    status varchar(5) not null DEFAULT 'ON',
    primary key(order_id)
);