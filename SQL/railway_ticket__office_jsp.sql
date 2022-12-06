drop database if exists Railway_Ticket_Office_JSP;

create database if not exists Railway_Ticket_Office_JSP;

USE Railway_Ticket_Office_JSP;

create table user_role(
                          id int primary key auto_increment,
                          user_role varchar(40)
);

create table user_info(
                          id int primary key auto_increment,
                          create_time timestamp not null,
                          update_time timestamp not null,
                          username varchar(64) unique not null,
                          first_name varchar(64) not null,
                          last_name varchar(64) not null,
                          user_password varchar(516) not null,
                          user_count_of_money DECIMAL(17,2) default 0.00,
                          account_verified boolean not null,
                          user_email varchar(64) unique not null,
                          role_id int,
                          foreign key(role_id) references user_role(id)
);

create table cities(
                       id int primary key auto_increment,
                       create_time timestamp,
                       update_time timestamp,
                       city_name varchar(32) not null unique,
                       relevant boolean
);

create table station(
                        id int primary key auto_increment,
                        create_time timestamp,
                        update_time timestamp,
                        station_name varchar(248) not null,
                        city_id int not null,
                        relevant boolean,
                        foreign key(city_id) references cities(id) ON DELETE CASCADE
);

create table train_info(
                           id int primary key auto_increment,
                           create_time timestamp,
                           update_time timestamp,
                           train_number varchar(20) not null,
                           number_of_seats int not null,
                           relevant boolean
);

create table route(
                      id int primary key auto_increment,
                      create_time timestamp,
                      update_time timestamp,
                      departure_city_id int not null,
                      arrival_city_id int not null,
                      start_station_id int not null,
                      departure_time timestamp not null,
                      travel_time time not null,
                      arrival_station_id int not null,
                      arrival_time timestamp not null,
                      train_id int not null,
                      number_of_free_seats int not null,
                      prise_of_ticket decimal(8, 2) NOT NULL,
                      relevant boolean,
                      foreign key(start_station_id) references station(id) ON DELETE CASCADE,
                      foreign key(arrival_station_id) references station(id) ON DELETE CASCADE,
                      foreign key(departure_city_id) references cities(id) ON DELETE CASCADE,
                      foreign key(arrival_city_id) references cities(id) ON DELETE CASCADE,
                      foreign key(train_id) references train_info(id) ON DELETE CASCADE
);

create table purchased_tickets(
                                  id int primary key auto_increment,
                                  create_time timestamp not null,
                                  update_time timestamp not null,
                                  order_prise decimal(8,2) not null,
                                  count_of_purchased_tickets int not null,
                                  order_status boolean,
                                  user_id int not null,
                                  route_id int not null,
                                  foreign key (user_id) references user_info(id),
                                  foreign key (route_id) references route(id)
);

insert into user_role(user_role) value
    ('USER'),('ADMIN');

insert into user_info(create_time,update_time,username,first_name,last_name,user_password,user_count_of_money,
                      account_verified,user_email,role_id)value
/*testuser*/
    ('2022-11-21 20:59:17','2022-11-21 20:59:17','Test','Test','Test','/D0z0RJAguDsDPFq7Y7xoUmv+2yw0UoUTPGaGb644wA=$G04XjCsOnR4vFTrt0PsJm2ozAiHflpS/O83Q6g/ifh8=','0.00',true,'testEmail@gmail.com',1),
/*testadmin*/
    ('2022-11-21 20:59:17','2022-11-21 20:59:17','TestAdmin','TestAdmin','TestAdmin','BJsiXw7nsvYiw6RMjxTkSX3lOqd72X9as6Ah/36wwZI=$HzCr9Ls2YDAcXCrqKsg7oZI6s7qXQCKLDtl69hCXguc=','0.00',true,'testadminEmail@gmail.com',2);

insert into cities(id,create_time,update_time,city_name,relevant) value
    (1,'2022-11-21 20:59:17','2022-11-21 20:59:17',' ињв',true),
    (2,'2022-11-21 20:59:17','2022-11-21 20:59:17','Ћьв≥в',true),
    (3,'2022-11-21 20:59:17','2022-11-21 20:59:17','ќдесса',true),
    (4,'2022-11-21 20:59:17','2022-11-21 20:59:17','’арк≥в',true),
    (5,'2022-11-21 20:59:17','2022-11-21 20:59:17','∆итомир',true);

insert into station(id,create_time,update_time,station_name,city_id,relevant) value
    (1,'2022-11-21 20:59:17','2022-11-21 20:59:17', ' ињв центр',1,true),
    (2,'2022-11-21 20:59:17','2022-11-21 20:59:17', ' ињв сх≥д',1,true),
    (3,'2022-11-21 20:59:17','2022-11-21 20:59:17', ' ињв зах≥д',1,true),
    (4,'2022-11-21 20:59:17','2022-11-21 20:59:17', 'Ћьв≥в центр',2,true),
    (5,'2022-11-21 20:59:17','2022-11-21 20:59:17', 'Ћьв≥в сх≥д',2,true),
    (6,'2022-11-21 20:59:17','2022-11-21 20:59:17', 'Ћьв≥в зах≥д',2,true),
    (7,'2022-11-21 20:59:17','2022-11-21 20:59:17', 'ќдесса центр',3,true),
    (8,'2022-11-21 20:59:17','2022-11-21 20:59:17', 'ќдесса зах≥д',3,true),
    (9,'2022-11-21 20:59:17','2022-11-21 20:59:17', '’арк≥в центр',4,true),
    (10,'2022-11-21 20:59:17','2022-11-21 20:59:17', '’арк≥в сх≥д',4,true),
    (11,'2022-11-21 20:59:17','2022-11-21 20:59:17', '∆итомир центр',5,true);

insert into train_info(id,create_time,update_time,train_number,number_of_seats,relevant) value
    (1,'2022-11-21 20:59:17','2022-11-21 20:59:17','O-100',120,true),
    (2,'2022-11-21 20:59:17','2022-11-21 20:59:17','O-101',100,true),
    (3,'2022-11-21 20:59:17','2022-11-21 20:59:17','O-102',110,true),
    (4,'2022-11-21 20:59:17','2022-11-21 20:59:17','O-103',90,true),
    (5,'2022-11-21 20:59:17','2022-11-21 20:59:17','O-104',125,true),
    (6,'2022-11-21 20:59:17','2022-11-21 20:59:17','O-105',95,true),
    (7,'2022-11-21 20:59:17','2022-11-21 20:59:17','K-95',100,true),
    (8,'2022-11-21 20:59:17','2022-11-21 20:59:17','K-96',120,true),
    (9,'2022-11-21 20:59:17','2022-11-21 20:59:17','K-97',110,true);

insert into route(id,create_time,update_time,departure_city_id,arrival_city_id,start_station_id,departure_time,travel_time,arrival_station_id
                 ,arrival_time,train_id,number_of_free_seats,prise_of_ticket,relevant) value
    (1,'2022-11-21 20:59:17','2022-11-21 20:59:17',1,2,1,'2022-11-27 19:15:06','05:30:00',4,'2022-11-28 02:30:09',1,100,800.00,true),
    (2,'2022-11-21 20:59:17','2022-11-21 20:59:17',1,2,2,'2022-11-27 19:15:06','05:30:00',5,'2022-11-28 02:30:09',2,80,750.00,true),
    (3,'2022-11-21 20:59:17','2022-11-21 20:59:17',1,4,3,'2022-11-27 19:15:06','05:30:00',9,'2022-11-28 02:30:09',4,90,1000.00,true),
    (4,'2022-11-21 20:59:17','2022-11-21 20:59:17',2,3,5,'2022-11-27 19:15:06','05:30:00',8,'2022-11-28 02:30:09',6,95,1050.00,true),
    (5,'2022-11-21 20:59:17','2022-11-21 20:59:17',2,1,6,'2022-11-27 19:15:06','05:30:00',2,'2022-11-28 02:30:09',3,110,800.00,true),
    (6,'2022-11-21 20:59:17','2022-11-21 20:59:17',4,5,10,'2022-11-27 19:15:06','05:30:00',11,'2022-11-28 02:30:09',5,125,650.00,true),
    (7,'2022-11-21 20:59:17','2022-11-21 20:59:17',1,2,3,'2022-11-27 19:15:06','05:30:00',4,'2022-11-28 02:30:09',8,120,900.00,true);
