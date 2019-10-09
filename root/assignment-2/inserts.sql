use assignment2;

insert into person values
(12, 'Alice', 'Wonder', 'alice', 'alice', 'alice@wonder.com', null),
(23, 'Bob', 'Marley', 'bob', 'bob', 'bob@marley.com', null),
(34, 'Charles', 'Garcia', 'charlie', 'charlie', 'chuch@garcia.com', null),
(45, 'Dan', 'Martin', 'dan', 'dan', 'dan@martin.com', null),
(56, 'Ed', 'Karaz', 'ed', 'ed', 'ed@kar.com', null);

insert into developer values
((select id from person where username = 'alice'), '4321rewq'),
((select id from person where username = 'bob'), '5432trew'),
((select id from person where username = 'charlie'), '6543ytre');

insert into user values
((select id from person where username = 'dan'), TRUE, '7654fda'),
((select id from person where username = 'ed'), TRUE, '5678dfgh');

insert into website values
(123, 'Facebook', 'an online social media and social networking service', curdate(), curdate(), 1234234),
(234, 'Twitter', 'an online news and social networking service', curdate(), curdate(), 4321543),
(345, 'Wikipedia', 'a free online encyclopedia', curdate(), curdate(), 3456654),
(456, 'CNN', 'an American basic cable and satellite television news channel', curdate(), curdate(), 6543345),
(567, 'CNET', 'an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics', curdate(), curdate(), 5433455),
(678, 'Gizmodo', 'a design, technology, science and science fiction website that also writes articles on politics', curdate(), curdate(), 4322345);

delimiter $
create procedure insert_website_role(in role_data varchar(10), in website_data varchar(32), in person_data varchar(32))
begin
    insert into website_role(role_id, website_id, developer_id)
    select role.id, website.id, person.id from role, website, person
    where role.id = role_data and website.name = website_data and person.username = person_data;
end$
delimiter ;

call insert_website_role('owner', 'Facebook', 'alice');
call insert_website_role('editor', 'Facebook', 'bob');
call insert_website_role('admin', 'Facebook', 'charlie');

call insert_website_role('owner', 'Twitter', 'bob');
call insert_website_role('editor', 'Twitter', 'charlie');
call insert_website_role('admin', 'Twitter', 'alice');

call insert_website_role('owner', 'Wikipedia', 'charlie');
call insert_website_role('editor', 'Wikipedia', 'alice');
call insert_website_role('admin', 'Wikipedia', 'bob');

call insert_website_role('owner', 'CNN', 'alice');
call insert_website_role('editor', 'CNN', 'bob');
call insert_website_role('admin', 'CNN', 'charlie');

call insert_website_role('owner', 'CNET', 'bob');
call insert_website_role('editor', 'CNET', 'charlie');
call insert_website_role('admin', 'CNET', 'alice');

call insert_website_role('owner', 'Gizmodo', 'charlie');
call insert_website_role('editor', 'Gizmodo', 'alice');
call insert_website_role('admin', 'Gizmodo', 'bob');

insert into page values
(123, 'Home', 'Landing page', (select makedate(2019, 247)), (select makedate(2019, 275)), 123434, 
    (select website.id from website where website.name = 'CNET')),
(234, 'About', 'Website description', (select makedate(2019, 247)), (select makedate(2019, 275)), 234545, 
    (select website.id from website where website.name = 'Gizmodo')),
(345, 'Contact', 'Addresses, phones, and contact info', (select makedate(2019, 247)), (select makedate(2019, 275)), 345656, 
    (select website.id from website where website.name = 'Wikipedia')),
(456, 'Preferences', 'Where users can configure their preferences', (select makedate(2019, 247)), (select makedate(2019, 275)), 456776, 
    (select website.id from website where website.name = 'CNN')),
(567, 'Profile', 'Users can configure their personal information', (select makedate(2019, 247)), (select makedate(2019, 275)), 567878, 
    (select website.id from website where website.name = 'CNET'));

delimiter $
create procedure insert_page_role(in role_data varchar(10), in page_data varchar(32), in person_data varchar(32))
begin
    insert into page_role(role_id, page_id, developer_id)
    select role.id, page.id, person.id from role, page, person
    where role.id = role_data and page.title = page_data and person.username = person_data;
end$
delimiter ;

call insert_page_role('editor', 'Home', 'alice');
call insert_page_role('reviewer', 'Home', 'bob');
call insert_page_role('writer', 'Home', 'charlie');

call insert_page_role('editor', 'About', 'bob');
call insert_page_role('reviewer', 'About', 'charlie');
call insert_page_role('writer', 'About', 'alice');

call insert_page_role('editor', 'Contact', 'charlie');
call insert_page_role('reviewer', 'Contact', 'alice');
call insert_page_role('writer', 'Contact', 'bob');

call insert_page_role('editor', 'Preferences', 'alice');
call insert_page_role('reviewer', 'Preferences', 'bob');
call insert_page_role('writer', 'Preferences', 'charlie');

call insert_page_role('editor', 'Profile', 'bob');
call insert_page_role('reviewer', 'Profile', 'charlie');
call insert_page_role('writer', 'Profile', 'alice');

insert into widget
(
    `id`, `name`, `dtype`, `width`, `height`, `css_class`, `css_style`,
    `page_id`, `order`, `text`, `size`, `html`, `src`, `url`, `shareable`, `expandable`
)
values
(123, 'head123', 'heading', null, null, null, null,
    (select page.id from page where page.title = 'Home'),
    0, 'Welcome', null, null, null, null, TRUE, TRUE),
(234, 'post234', 'html', null, null, null, null,
    (select page.id from page where page.title = 'About'),
    0, '<p>Lorem</p>', null, null, null, null, TRUE, TRUE),
(345, 'head345', 'heading', null, null, null, null,
    (select page.id from page where page.title = 'Contact'),
    1, 'Hi', null, null, null, null, TRUE, TRUE),
(456, 'intro456', 'html', null, null, null, null,
    (select page.id from page where page.title = 'Contact'),
    2, '<h1>Hi</h1>', null, null, null,  null, TRUE, TRUE),
(567, 'image345', 'image', 50, 100, null, null,
    (select page.id from page where page.title = 'Contact'),
    3, null, null, null, null, '/img/567.png', TRUE, TRUE),
(678, 'video456', 'youtube', 400, 300, null, null,
    (select page.id from page where page.title = 'Preferences'),
    0, null, null, null, null, 'https://youtu.be/h67VX51QXiQ', TRUE, TRUE);

insert into phone (phone, `primary`, person_id) values
('123-234-3456', TRUE, (select person.id from person where person.username = 'alice')),
('234-345-4566', FALSE, (select person.id from person where person.username = 'alice')),
('345-456-5677', TRUE, (select person.id from person where person.username = 'bob')),
('321-432-5435', TRUE, (select person.id from person where person.username = 'charlie')),
('432-432-5433', FALSE, (select person.id from person where person.username = 'charlie')),
('543-543-6544', FALSE, (select person.id from person where person.username = 'charlie'));

insert into address (street, city, state, zip, `primary`, person_id) values
('123 Adam St.', 'Alton', null, '01234', TRUE, (select person.id from person where person.username = 'alice')),
('234 Birch St.', 'Boston', null, '02345', FALSE, (select person.id from person where person.username = 'alice')),
('345 Charles St.', 'Chelms', null, '03455', TRUE, (select person.id from person where person.username = 'bob')),
('456 Down St.', 'Dalton', null, '04566', FALSE, (select person.id from person where person.username = 'bob')),
('543 East St.', 'Everett', null, '01112', FALSE, (select person.id from person where person.username = 'bob')),
('654 Frank St.', 'Foulton', null, '04322', TRUE, (select person.id from person where person.username = 'charlie'));
