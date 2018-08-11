drop database if exists demo;
create database demo default character set utf8;
use demo;
drop table if exists product;
drop table if exists category;

create table category
(
   cat_id                  int not null auto_increment,
   cat_name                varchar(20),
   primary key (cat_id)
);

create table product
(
   pro_id                  int not null auto_increment,
   pro_name                varchar(20),
   pro_price               decimal(8,2),
   pro_remark              longtext,
   pro_date                timestamp default CURRENT_TIMESTAMP,
   cat_id                  int,
   primary key (pro_id)
);

insert into category (cat_name) values ('商务正装');

/* 商品测试用例 */
insert into product (pro_name,pro_price,pro_remark,cat_id) values ('圣得西服',3000.00,'这里是简单介绍',1);

select * from product;
select * from category;