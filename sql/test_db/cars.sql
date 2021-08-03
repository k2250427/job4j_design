create table bodies (
    id serial  primary key,
	name varchar(255)
);

create table engines (
    id serial  primary key,
	name varchar(255)
);

create table boxes (
    id serial  primary key,
	name varchar(255)
);

create table vehicle (
    id serial  primary key,
	name varchar(255),
	body_id int references bodies(id),
	engine_id int references engines(id),
	box_id int references boxes(id)
);

insert into bodies(name) values('седан');
insert into bodies(name) values('хэтчбэк');
insert into bodies(name) values('пикап');
insert into bodies(name) values('самосвал');

insert into engines(name) values('бензиновый');
insert into engines(name) values('дизельный');
insert into engines(name) values('реактивный');

insert into engines(name) values('автомат');
insert into engines(name) values('механика');
insert into engines(name) values('картонная');


insert into vehicle(name,body_id,engine_id,box_id) values('БМВ',1,1,1);
insert into vehicle(name,body_id,engine_id,box_id) values('Опель',2,2,2);
insert into vehicle(name,body_id,engine_id,box_id) values('Ниссан',3,2,1);
insert into vehicle(name,body_id,engine_id,box_id) values('Фольксваген',2,1,2);

select veh.name car, bod.name body, e.name engine, box.name box from vehicle veh 
left join bodies bod on veh.body_id=bod.id
left join engines e on veh.engine_id=e.id
left join boxes box on veh.box_id=box.id;

select bod.name body from vehicle veh right join bodies bod on veh.body_id=bod.id where veh.id is null;

select e.name body from vehicle veh right join engines e on veh.engine_id=e.id where veh.id is null;

select box.name body from vehicle veh right join boxes box on veh.box_id=box.id where veh.id is null;
