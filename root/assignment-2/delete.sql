/* use assignment2; */

/* Delete developer - Delete Alice's primary address */
delete from address
where person_id = (select id from person where person.username = 'alice')
and `primary` = 1;

/* Delete widget - Remove the last widget in the Contact page. The last widget is the one with the highest value in the order field */
delete from widget
where widget.id =
(
    select id from
    (
        select widget.* from widget join page on widget.page_id = page.id
        where page.title = 'Contact'
        order by widget.`order` desc limit 1
    ) as widget2
);

/* Delete page - Remove the last updated page in Wikipedia */
select page.id from page
where page.updated = 
(
    select max(updated) from page 
    where page.website_id = 
    (select id from website where website.name = 'Wikipedia')
)
and page.website_id = (select id from website where website.name = 'Wikipedia')
into @pageid;

delete from widget where widget.page_id = @pageid;
delete from page_role where page_role.page_id = @pageid;
delete from page where page.id = @pageid;

/* Delete website - Remove the CNET web site, as well as all related roles and privileges relating developers to the Website and Pages */
select website.id from website where website.name = 'CNET' into @webid;
delete from website_role where website_role.website_id = @webid;
delete from page_role
where page_role.page_id in
(
    select page.id from page
    where page.website_id = @webid
);
delete from widget
where widget.page_id in
(
    select page.id from page
    where page.website_id = @webid
);
delete from page where page.website_id = @webid;
delete from website where website.id = @webid;

