insert into roles(name) values ('Admin');
insert into roles(name) values ('User');

insert into users(name,role_id) values ('Ivan',1);
insert into users(name,role_id) values ('Kirill',2);
insert into users(name,role_id) values ('Roman',2);

insert into rules(name) values ('create');
insert into rules(name) values ('read');
insert into rules(name) values ('update');
insert into rules(name) values ('delete');

insert into categories(name) values ('urgent');
insert into categories(name) values ('ordinary');

insert into states(name) values ('active');
insert into states(name) values ('closed');

insert into items(name,user_id,category_id,state_id) values ('Update data',2,1,1);
insert into items(name,user_id,category_id,state_id) values ('Create DB backup',1,2,1);

insert into comments(name,item_id) values ('Some comment',1);

insert into attachs(name,item_id) values ('result.txt',1);

insert into role_rule(role_id,rule_id) values (1,1);
insert into role_rule(role_id,rule_id) values (1,2);
insert into role_rule(role_id,rule_id) values (1,3);
insert into role_rule(role_id,rule_id) values (1,4);
insert into role_rule(role_id,rule_id) values (2,2);
insert into role_rule(role_id,rule_id) values (2,3);