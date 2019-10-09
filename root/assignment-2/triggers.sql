/* use assignment2; */

delimiter $
create trigger create_website_privilege
    after insert on website_role
        for each row
        begin
            if new.role_id = 'owner' or new.role_id = 'admin' then
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'create');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'read');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'update');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'delete');
            elseif new.role_id = 'writer' then
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'create');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'read');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'update');
            elseif new.role_id = 'editor' then
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'read');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'update');
            elseif new.role_id = 'reviewer' then
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'read');
            end if;
        end$
delimiter ;

delimiter $
create trigger update_website_privilege
    after update on website_role
        for each row
        begin
            delete from website_privilege where website_id = new.website_id and developer_id = new.developer_id;
            if new.role_id = 'owner' or new.role_id = 'admin' then
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'create');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'read');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'update');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'delete');
            elseif new.role_id = 'writer' then
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'create');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'read');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'update');
            elseif new.role_id = 'editor' then
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'read');
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'update');
            elseif new.role_id = 'reviewer' then
                insert into website_privilege(website_id, developer_id, privilege_id) values (new.website_id, new.developer_id, 'read');
            end if;
        end$
delimiter ;

delimiter $
create trigger delete_website_privilege
    before delete on website_role
        for each row
        begin
            delete from website_privilege where website_id = old.website_id and developer_id = old.developer_id;
        end$
delimiter ;

delimiter $
create trigger create_page_privilege
    after insert on page_role
        for each row
        begin
            if new.role_id = 'owner' or new.role_id = 'admin' then
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'create');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'read');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'update');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'delete');
            elseif new.role_id = 'writer' then
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'create');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'read');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'update');
            elseif new.role_id = 'editor' then
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'read');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'update');
            elseif new.role_id = 'reviewer' then
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'read');
            end if;
        end$
delimiter ;

delimiter $
create trigger update_page_privilege
    after update on page_role
        for each row
        begin
            delete from page_privilege where page_id = new.page_id and developer_id = new.developer_id;
            if new.role_id = 'owner' or new.role_id = 'admin' then
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'create');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'read');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'update');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'delete');
            elseif new.role_id = 'writer' then
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'create');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'read');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'update');
            elseif new.role_id = 'editor' then
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'read');
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'update');
            elseif new.role_id = 'reviewer' then
                insert into page_privilege(page_id, developer_id, privilege_id) values (new.page_id, new.developer_id, 'read');
            end if;
        end$
delimiter ;

delimiter $
create trigger delete_page_privilege
    after delete on page_role
        for each row
        begin
            delete from page_privilege where page_id = old.page_id and developer_id = old.developer_id;
        end$
delimiter ;