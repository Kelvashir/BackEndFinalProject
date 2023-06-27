insert into vehicle_owner (
	vehicle_owner_id,
	vehicle_owner_first_name,
	vehicle_owner_last_name,
	vehicle_owner_phone,
	vehicle_owner_email)
values (1, 'Tom','Tompson','123-456-7890','tomtom@acc.com');

insert into vehicle_owner (
	vehicle_owner_id,
	vehicle_owner_first_name,
	vehicle_owner_last_name,
	vehicle_owner_phone,
	vehicle_owner_email)
values (2, 'Jerry','Jerrypson','987-654-3210','jerjer@acc.com');

insert into vehicle (
	vehicle_id,
	vehicle_owner_id,
	vehicle_year,
	vehicle_make,
	vehicle_model,
	vehicle_engine,
	vehicle_mileage)
	
values (1,1,'2020','Nissan','Sentra','2.5L',50);

insert into vehicle (
	vehicle_id,
	vehicle_owner_id,
	vehicle_year,
	vehicle_make,
	vehicle_model,
	vehicle_engine,
	vehicle_mileage)
	
values (2,1,'2022','Nissan','Maxima','3.5L',25);

insert into vehicle (
	vehicle_id,
	vehicle_owner_id,
	vehicle_year,
	vehicle_make,
	vehicle_model,
	vehicle_engine,
	vehicle_mileage)
	
values (3,2,'2020','Chrysler','Voyager','3.6L',50);

insert into vehicle (
	vehicle_id,
	vehicle_owner_id,
	vehicle_year,
	vehicle_make,
	vehicle_model,
	vehicle_engine,
	vehicle_mileage)
	
values (4,2,'2022','Chrysler','300S','5.7L',25);

insert into repair_shop (
	repair_shop_id,
	repair_shop_name,
	repair_shop_street_address,
	repair_shop_city,
	repair_shop_state,
	repair_shop_zip,
	repair_shop_phone)
	
values (1,'Batmans Beamers','1234 Wayne Lane','Gotham City','Dunnanana','48635','456-789-1230');
	
insert into repair_shop (
	repair_shop_id,
	repair_shop_name,
	repair_shop_street_address,
	repair_shop_city,
	repair_shop_state,
	repair_shop_zip,
	repair_shop_phone)

values (2,'Supermans Subas','4321 Lois Lane','Metropolis','Dundadala','87345','789-132-4560');

insert into vehicle_owner_repair_shop (
	vehicle_owner_id,
	repair_shop_id)
	
values (1,1);

insert into vehicle_owner_repair_shop (
	vehicle_owner_id,
	repair_shop_id)
	
values (2,1);

insert into vehicle_owner_repair_shop (
	vehicle_owner_id,
	repair_shop_id)
	
values (2,2);

