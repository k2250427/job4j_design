create table authors(
	id serial primary key,
	name varchar(255),
	age integer,
	comment text
)
insert into authors(name,age,comment) values('Том Клэнси', 66, 'писатель');
update authors set age = 67;
delete from authors;