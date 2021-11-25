CREATE TABLE users(
    id int(3) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    first_name varchar(225) DEFAULT NULL,
    last_name varchar(225) DEFAULT NULL,
    username varchar(225) DEFAULT NULL,
    password varchar(225) DEFAULT NULL
);

CREATE TABLE todos(
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    description varchar(225) DEFAULT NULL,
    is_done bit(1) NOT NULL,
    target_date datetime(6) DEFAULT NULL,
    username varchar(225) DEFAULT NULL,
    title varchar(225) DEFAULT NULL
) AUTO_INCREMENT=8;