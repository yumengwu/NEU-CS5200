/* use assignment2; */

create view deleveloper_roles_and_privileges as
select firstname, lastname, username, email, website_name, website_updated, website_visits,
website_role, website_privileges, page_title, page_updated, page_views, page_role, page_privileges
from
(
    select w.name as website_name, w.updated as website_updated, w.visits as website_visits, p.developer_id,
    p.role_id as website_role, p.privileges as website_privileges, null as page_title, null as page_updated,
    null as page_views, null as page_role, null as page_privileges
    from website w right join 
    (
        select p.website_id, p.developer_id, r.role_id, p.privileges
        from website_role r right join 
        (
            select developer_id, website_id, group_concat(privilege_id separator ', ') as privileges
            from website_privilege group by developer_id, website_id
        ) as p on r.website_id = p.website_id and r.developer_id = p.developer_id
    ) as p on w.id = p.website_id
    union all
    select null as website_name, null as website_updated, null as website_visits, p.developer_id,
    null as website_role, null as website_privileges, w.title as page_title, w.updated as page_updated,
    w.views as page_views, p.role_id as page_role, p.privileges as page_privileges
    from page w right join 
    (
        select p.page_id, p.developer_id, r.role_id, p.privileges
        from page_role r right join 
        (
            select developer_id, page_id, group_concat(privilege_id separator ', ') as privileges
            from page_privilege group by developer_id, page_id
        ) as p on r.page_id = p.page_id and r.developer_id = p.developer_id
    ) as p on w.id = p.page_id
) as role_and_privilege left join person on person.id = role_and_privilege.developer_id order by person.id;
