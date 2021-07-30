create table authors(
	id serial primary key,
	name varchar(255),
	age int,
	comment text
);

create table publishers(
	id serial primary key,
	name varchar(255),
	comment text
);

create table storages(
	id serial primary key,
	rack varchar(10),
	shelf varchar(10),
	comment text
);

create table books(
	id serial primary key,
	name varchar(255),
	public_date date,
	publisher_id int references publishers(id),
	storage_id int references storages(id) unique,
	comment text
);

create table author_book(
    author_id int references author(id) unique,
    book_id int references book(id) unique
);