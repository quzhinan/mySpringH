#mysql数据库
create database springMVC DEFAULT CHARACTER SET utf8 ;
use springMVC;
create table admin_user(
 id int primary key auto_increment not null,
 username varchar(10) not null,
 password varchar(30) not null,
 age int ,
 sex int ,
 address varchar(50),
 createdate timestamp,
 updatedate timestamp
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO admin_user(username, password) VALUES('user1', '123');
INSERT INTO admin_user(username, password) VALUES('user1', '123');
