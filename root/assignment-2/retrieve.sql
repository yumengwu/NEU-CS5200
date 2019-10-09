use assignment2;

/* 1. Retrieve developers */
/* Retrieve all developers */
select firstname, lastname, username, email
from developer join person 
on developer.id = person.id;

/* Retrieve a developer with id equal to 34 */
select firstname, lastname, username, email
from developer join person 
on developer.id = person.id
where developer.id = 34;

/* Retrieve all developers who have a role in Twitter other than owner */
select firstname, lastname, username, email
from
(
    select developer_id from
    (
        select * from website_role where role_id <> 'owner'
    ) as web_role left join website on website.id = web_role.website_id
    where name = 'Twitter'
) as dev_id left join person on dev_id.developer_id = person.id
group by person.username;

/* Retrieve all developers who are page reviewers of pages with less than 300000 visits */
select firstname, lastname, username, email
from
(
    select developer_id from
    (
        select id from page where views < 300000
    ) as pages left join
    (
        select page_id, developer_id, role_id
        from page_role
        where page_role.role_id = 'reviewer'
    ) as roles
    on pages.id = roles.page_id
) as dev_id left join person
on dev_id.developer_id = person.id
group by person.username;

/* Retrieve the writer developer who added a heading widget to CNET's home page */
select firstname, lastname, username, email
from person, page_role, widget, page, website
where website.name = 'CNET'
and page.website_id = website.id
and widget.page_id = page.id
and page_role.page_id = page.id
and page_role.role_id = 'writer'
and person.id = page_role.developer_id;

/* 2. Retrieve websites */
/* Retrieve the website with the least number of visits */
select name from website
where website.visits = 
(
    select min(website.visits) from website
);

/* Retrieve the name of a website whose id is 678 */
select name from website where website.id = 678;

/* Retrieve all websites with videos reviewed by bob */
select website.name, page.title
from person, website, page, page_role, widget
where page_role.role_id = 'reviewer'
and page.id = page_role.page_id
and page_role.developer_id = person.id
and person.username = 'bob'
and widget.page_id = page.id
and widget.dtype = 'youtube'
and page.website_id = website.id;

/* Retrieve all websites where alice is an owner */
select website.name from website_role, website
where website_role.role_id = 'owner'
and developer_id = (select person.id from person where person.username = 'alice')
and website.id = website_role.website_id;

/* Retrieve all websites where charlie is an admin and get more than 6000000 visits */
select website.name from website, website_role
where website_role.developer_id = (select person.id from person where person.username = 'charlie')
and website_role.role_id = 'admin'
and website.id = website_role.website_id
and website.visits > 6000000;

/* Retrieve pages */
/* Retrieve the page with the most number of views */
select page.title from page where page.views = (select max(views) from page);

/* Retrieve the title of a page whose id is 234 */
select page.title from page where page.id = 234;

/* Retrieve all pages where alice is an editor */
select page.title
from
(
    select page_id from page_role, person
    where person.username = 'alice'
    and person.id = page_role.developer_id
    and page_role.role_id = 'editor'
) as pr left join page on pr.page_id = page.id;

/* Retrieve the total number of pageviews in CNET */
select sum(views) from page, website
where website.name = 'CNET'
and page.website_id = website.id;

/* Retrieve the average number of page views in the Web site Wikipedia */
select avg(views) from website, page
where website.name = 'Wikipedia'
and page.website_id = website.id;

/* Retrieve widgets */
/* Retrieve all widgets in CNET's Home page */
select widget.name from widget
where widget.page_id =
(
    select id from page
    where page.website_id = (select website.id from website where website.name = 'CNET')
    and page.title = 'Home'
);

/* Retrieve all youtube widgets in CNN */
select widget.name from widget join
(
    select page.id from page, website
    where website.name = 'CNN'
    and page.website_id = website.id
) as pid on widget.page_id = pid.id;

/* Retrieve all image widgets on pages reviewed by Alice */
select widget.name from widget
where widget.page_id =
(
    select page.id from page, page_role
    where page_role.developer_id = (select person.id from person where person.username = 'alice')
    and page_role.role_id = 'reviewer'
    and page.id = page_role.page_id
)
and widget.dtype = 'image';

/* Retrieve how many widgets are in Wikipedia */
select count(id) from widget
where widget.page_id =
(
    select page.id from page, website
    where website.name = 'Wikipedia'
    and page.website_id = website.id
);

/* To verify the page and website triggers written earlier function properly */
/* Retrieve the names of all the websites where Bob has DELETE privileges */
select website.name from
(
    select website_id from website_privilege
    where developer_id = (select person.id from person where person.username = 'bob')
    and privilege_id = 'delete'
) as webid left join website on webid.website_id = website.id;

/* Retrieve the names of all the pages where Charlie has CREATE privileges */
select page.title from
(
    select page_id from page_privilege
    where developer_id = (select person.id from person where person.username = 'charlie')
    and privilege_id = 'create'
) as pid left join page on pid.page_id = page.id;
