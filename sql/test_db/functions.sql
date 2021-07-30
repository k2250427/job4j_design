create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name,price) values('Тостер',2500);
insert into devices(name,price) values('Холодильник',12500);
insert into devices(name,price) values('Телевизор',52500);
insert into devices(name,price) values('Стиральная машина',22000);

insert into people(name) values('Вася Самосвалов');
insert into people(name) values('Жора Иванов');
insert into people(name) values('Сидор Петрович');

insert into devices_people(device_id,people_id) values(1,1);
insert into devices_people(device_id,people_id) values(2,1);
insert into devices_people(device_id,people_id) values(3,2);
insert into devices_people(device_id,people_id) values(4,3);
insert into devices_people(device_id,people_id) values(2,2);

select average(price) from devices;

select pe.name as user, avg(price) from devices dev inner join devices_people dp on dev.id=dp.device_id
inner join people pe on dp.people_id=pe.id group by pe.name;

select pe.name as user, avg(price) from devices dev inner join devices_people dp on dev.id=dp.device_id
inner join people pe on dp.people_id=pe.id where dev.price>5000 group by pe.name;