/* use assignment2; */

/* Update developer - Update Charlie's primary phone number to 333-444-5555 */
update phone
set phone.phone = '333-444-5555'
where `primary` = 1
and person_id = (select person.id from person where person.username = 'charlie');

/* Update widget - Update the relative order of widget head345 on the page so that it's new order is 3 */
update widget
set `order` = 
case
when widget.name = 'head345' then 3
when widget.`order` <= 3 and widget.name <> 'head345' then widget.`order` - 1
else widget.`order`
end
where page_id = (select page_id from (select * from widget where widget.name = 'head345') as w);

/* Update page - Append 'CNET - ' to the beginning of all CNET's page titles */
update page
set title=concat('CNET - ', title)
where website_id = (select id from website where website.name = 'CNET');

/* Update roles - Swap Charlie's and Bob's role in CNET's Home page */
select page_role.role_id from page, website, page_role
where website.name = 'CNET'
and page.website_id = website.id
and (page.title = 'Home' or page.title = 'CNET - Home')
and page_role.page_id = page.id
and page_role.developer_id = (select id from person where person.username = 'charlie')
into @charlie;

select page_role.role_id from page, website, page_role
where website.name = 'CNET'
and page.website_id = website.id
and (page.title = 'Home' or page.title = 'CNET - Home')
and page_role.page_id = page.id
and page_role.developer_id = (select id from person where person.username = 'bob')
into @bob;

update page_role
set role_id = @charlie
where page_role.developer_id = (select id from person where person.username = 'bob')
and page_role.page_id = 
(
    select page.id from page, website 
    where website.name = 'CNET' 
    and page.website_id = website.id 
    and (page.title = 'Home' or page.title = 'CNET - Home')
);

update page_role
set role_id = @bob
where page_role.developer_id = (select id from person where person.username = 'charlie')
and page_role.page_id = 
(
    select page.id from page, website 
    where website.name = 'CNET' 
    and page.website_id = website.id 
    and (page.title = 'Home' or page.title = 'CNET - Home')
);
