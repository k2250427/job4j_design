create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
    price float
);

insert into type(name) values('сыр');
insert into type(name) values('молоко');
insert into type(name) values('прочее');

insert into product(name,type_id,expired_date,price) values('сыр',1,'2021-07-01',250);
insert into product(name,type_id,expired_date,price) values('сыр плавленный',1,'2021-08-01',150);
insert into product(name,type_id,expired_date,price) values('сыр дорогой',1,'2021-09-01',1500);
insert into product(name,type_id,expired_date,price) values('молоко 1 л.',2,'2021-08-01',70);
insert into product(name,type_id,expired_date,price) values('мороженое Рожок',3,'2021-08-01',50);
insert into product(name,type_id,expired_date,price) values('мороженое Эскимо',3,'2021-08-01',60);

select pr.name as product, ty.name as type, pr.expired_date, pr.price 
from product pr inner join type ty on pr.type_id=ty.id where ty.name = 'сыр';

select pr.name as product, ty.name as type, pr.expired_date, pr.price 
from product pr inner join type ty on pr.type_id=ty.id where pr.name like '%мороженое%';

select pr.name as product, ty.name as type, pr.expired_date, pr.price 
from product pr inner join type ty on pr.type_id=ty.id where pr.expired_date<now();

select pr.name as product, pr.expired_date, pr.price 
from product pr inner join (select max(p.price) max_price from product p) as tmp on pr.price = tmp.max_price;

select ty.name as type, count(pr.name) as qty from product pr inner join type ty on pr.type_id=ty.id group by ty.name;

select pr.name as product, ty.name as type, pr.expired_date, pr.price 
from product pr inner join type ty on pr.type_id=ty.id where ty.name = 'сыр' or ty.name = 'молоко';

select pr.name as product, ty.name as type, pr.expired_date, pr.price 
from product pr inner join type ty on pr.type_id=ty.id
inner join (select ty.id as type from product pr inner join type ty on pr.type_id=ty.id 
group by ty.id having count(pr.name)<10) tmp on pr.type_id=tmp.type;

select pr.name as product, ty.name as type, pr.expired_date, pr.price 
from product pr inner join type ty on pr.type_id=ty.id;