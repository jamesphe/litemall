drop database if exists techlife;
drop user if exists 'techlife'@'%';
-- 支持emoji：需要mysql数据库参数： character_set_server=utf8mb4
create database techlife default character set utf8mb4 collate utf8mb4_unicode_ci;
use techlife;
create user 'techlife'@'%' identified by 'techlife#2020';
grant all privileges on techlife.* to 'techlife'@'%';
flush privileges;
