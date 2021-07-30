select it.name item, us.name as user from items it inner join users us on it.user_id=us.id;

select it.name item, cat.name category from items it inner join categories cat on it.category_id=cat.id;

select us.name as user, ro.name category from users us inner join roles ro on us.role_id=ro.id;