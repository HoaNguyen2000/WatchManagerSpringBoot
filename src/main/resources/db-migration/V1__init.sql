create table if not exists users(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    email varchar(50) DEFAULT NULL,
    name varchar(200) DEFAULT NULL,
    password varchar(120) DEFAULT NULL,
    phone varchar(255) DEFAULT NULL,
    username varchar(20) DEFAULT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
ALTER TABLE users
    ADD CONSTRAINT UC_Users UNIQUE (username, email);

CREATE TABLE if not exists roles (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(20) DEFAULT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE if not exists user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    PRIMARY KEY (`user_id`,`role_id`),
    CONSTRAINT fk_user_role_id FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
    );

CREATE TABLE if not exists products (
    id bigint NOT NULL AUTO_INCREMENT,
    brand_id bigint DEFAULT NULL,
    description text,
    name varchar(255) DEFAULT NULL,
    old_price double DEFAULT NULL,
    price double DEFAULT NULL,
    slug varchar(255) DEFAULT NULL,
    type varchar(255) DEFAULT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );
ALTER TABLE products
    ADD CONSTRAINT UC_Products UNIQUE (slug);

CREATE TABLE if not exists specification (
    id bigint NOT NULL AUTO_INCREMENT,
    product_id bigint NOT NULL,
    battery varchar(255) DEFAULT NULL,
    display_size varchar(255) DEFAULT NULL,
    display_type varchar(255) DEFAULT NULL,
    launch_announced date DEFAULT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );

CREATE TABLE if not exists brands (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    slug varchar(255) DEFAULT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    );
ALTER TABLE products
    ADD CONSTRAINT UC_Brands UNIQUE (slug);

