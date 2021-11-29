DROP TABLE todos;
DROP TABLE users;

CREATE TABLE users(
    user_id int(3) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    first_name varchar(225) NOT NULL,
    last_name varchar(225) NOT NULL,
    username varchar(225) NOT NULL,
    password varchar(225) NOT NULL
);

CREATE TABLE todos(
    task_id bigint(20) NOT NULL AUTO_INCREMENT,
    task_name varchar(225) NOT NULL,
    is_done bit(1) NOT NULL,
    target_date datetime(6) DEFAULT NULL,
    FK_user_id_users int(3) NOT NULL,
    PRIMARY KEY(task_id),
    FOREIGN KEY(FK_user_id_users) REFERENCES users(user_id)
) AUTO_INCREMENT=8;