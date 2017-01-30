--用户表
create table if not exists oj_users (
	user_id bigint(20) not null auto_increment primary key,
	username varchar(32) not null,
	password varchar(32) not null,
	email varchar(64) not null,
	phone varchar(32) ,
	ip varchar(32) ,
	school varchar(32) ,
	submitted int not null default 0,
	solved int not null default 0
)DEFAULT charset=UTF8 auto_increment=1;

create table if not exists model_info(
	model_id int not null auto_increment primary key,
	model_name varchar(16) not null,
	model_description varchar(64) not null,
	local_url varchar(64) not null,
	create_time datetime not null,
	role_id int not null
)DEFAULT charset=UTF8 auto_increment=1;

create table if not exists role_info(
	role_id int not null primary key,
	role_name varchar(16) not null,
	role_description varchar(64) not null,
	create_time datetime not null,
	salary double 
)DEFAULT charset=UTF8 auto_increment=1;
--插入用户数据
insert into oj_users(username,password,email) values
	('creep','e10adc3949ba59abbe56e057f20f883e','429391630@qq.com'),
	('yenvhang','e10adc3949ba59abbe56e057f20f883e','tmac.back@163.com');
	
	
CREATE TABLE `oj_problems` (
  `problem_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text CHARACTER SET latin1 NOT NULL,
  `content` text CHARACTER SET utf8 NOT NULL,
  `input` text CHARACTER SET latin1 NOT NULL,
  `outptu` text CHARACTER SET latin1 NOT NULL,
  `input_sample` text CHARACTER SET latin1,
  `out_sample` text CHARACTER SET latin1,
  `hint` text CHARACTER SET latin1,
  `join_data` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `memory_limit` int(11) NOT NULL,
  `time_limit` int(11) NOT NULL,
  `submitted` int(11) NOT NULL DEFAULT '0',
  `solved` int(11) NOT NULL DEFAULT '0',
  `level` varchar(10) NOT NULL DEFAULT '1',
  `author` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`problem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4;


insert into oj_problems values('title','content','input','output'
	,'input_sample','out_sample','hint','join_data','memory_limit'
	,'time_limit','submitted','solved','level','author');

