drop table if exists vehicle_owner_repair_shop;
drop table if exists repair_shop;
drop table if exists vehicle;
drop table if exists vehicle_owner;


create table vehicle_owner
(
   vehicle_owner_id int not null auto_increment,
   vehicle_owner_first_name varchar (20) not null,
   vehicle_owner_last_name varchar (40) not null,
   vehicle_owner_phone varchar (20),
   vehicle_owner_email varchar (40),
   primary key (vehicle_owner_id)
);
create table vehicle
(
   vehicle_id int not null auto_increment,
   vehicle_owner_id int not null,
   vehicle_year varchar (4),
   vehicle_make varchar (20),
   vehicle_model varchar (20),
   vehicle_engine varchar (20),
   vehicle_mileage int,
   primary key (vehicle_id),
   foreign key (vehicle_owner_id) references vehicle_owner (vehicle_owner_id) on delete cascade   
);
create table repair_shop
(
   repair_shop_id int not null auto_increment,
   repair_shop_name varchar (40),
   repair_shop_street_address varchar (40),
   repair_shop_city varchar (40),
   repair_shop_state varchar (20),
   repair_shop_zip varchar (20),
   repair_shop_phone varchar (20),
   primary key (repair_shop_id)
);
create table vehicle_owner_repair_shop
(
   vehicle_owner_id int not null,
   repair_shop_id int not null,
   foreign key (vehicle_owner_id) references vehicle_owner (vehicle_owner_id) on delete cascade,
   foreign key (repair_shop_id) references repair_shop (repair_shop_id) on delete cascade
);