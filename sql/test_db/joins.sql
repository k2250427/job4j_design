create table departments (
    id serial  primary key,
	name varchar(255)
);

create table emploees (
    id serial  primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values('Склад');
insert into departments(name) values('Цех');
insert into departments(name) values('Контора');
insert into departments(name) values('Уволенные');

insert into emploees(name,department_id) values('Вася Самосвалов',1);
insert into emploees(name,department_id) values('Жора Иванов',2);
insert into emploees(name,department_id) values('Дима Сидоров',2);
insert into emploees(name,department_id) values('Лена Петрова',3);
insert into emploees(name,department_id) values('Свирид Прокофьевич',3);

select dep.name department, emp.name employee from departments dep left join emploees emp on dep.id=emp.department_id;

select dep.name department, emp.name employee from emploees emp right join departments dep on dep.id=emp.department_id;

select dep.name department, emp.name employee from departments dep full join emploees emp on dep.id=emp.department_id;

select dep.name department, emp.name employee from emploees emp cross join departments dep;

select dep.name department, emp.name employee from departments dep left join emploees emp on dep.id=emp.department_id where emp.name is null;

create table teens (
    id serial  primary key,
	name varchar(255),
	gender varchar(7)
);

insert into teens(name,gender) values('Вася Самосвалов','мальчик');
insert into teens(name,gender) values('Жора Иванов','мальчик');
insert into teens(name,gender) values('Дима Сидоров','мальчик');
insert into teens(name,gender) values('Лена Петрова','девочка'); 
insert into teens(name,gender) values('Света Цветкова','девочка'); 

select teen1.name boy, teen2.name girl from teens teen1 cross join teens teen2 where teen1.gender='мальчик' and teen2.gender='девочка';