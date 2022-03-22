create table product (
     id SERIAL PRIMARY KEY,
     name varchar(50) NOT NULL,
     price NUMERIC(9, 2) NOT NULL
);

insert into product (name, price) VALUES ('pie', 150);
insert into product (name, price) VALUES ('cake', 200);
insert into product (name, price) VALUES ('burger', 100.01);
