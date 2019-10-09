create schema assignment2;

use assignment2;

create table person (
    id int not null auto_increment,
    firstname varchar(32) not null,
    lastname varchar(32) not null,
    username varchar(32) not null,
    password varchar(32) not null,
    email varchar(40) not null,
    dob char(10),
    primary key (id),
    unique (username),
    unique (email)
);

create unique index person_username on person(username);

create table developer (
    id int not null,
    developer_key char(32) not null,
    primary key (id),
    foreign key (id) references person(id),
    unique (developer_key)
);

create table user (
    id int not null,
    user_agreement boolean not null,
    user_key char(32) not null,
    primary key (id),
    foreign key (id) references person(id),
    unique (user_key)
);

create table website (
    id int not null auto_increment,
    name varchar(32) not null,
    description varchar(256),
    created date not null,
    updated date not null,
    visits int,
    primary key (id)
);

create unique index website_name on website(name);

create table page (
    id int not null auto_increment,
    title varchar(32) not null,
    description varchar(256),
    created date not null,
    updated date not null,
    views int,
    website_id int not null,
    primary key (id),
    foreign key (website_id) references website(id)
);

create index page_website_id on page(website_id);

create unique index page_title on page(title);

create table dtype (
    id varchar(10) not null,
    primary key (id)
);

insert into dtype values
('image'),
('heading'),
('html'),
('youtube');

create table widget (
    `id` int not null auto_increment,
    `name` varchar(32) not null,
    `dtype` varchar(10) not null,
    `width` int,
    `height` int,
    `css_class` varchar(32),
    `css_style` varchar(32),
    `page_id` int not null,
    `order` int not null,
    `text` varchar(32) null,
    size int default 2,
    html varchar(64),
    src varchar(64),
    url varchar(64),
    shareable boolean,
    expandable boolean,
    primary key (id),
    foreign key (page_id) references page(id),
    foreign key (dtype) references dtype(id)
);

create index widget_page_id on widget(page_id);

create table phone (
    id int not null auto_increment,
    phone varchar(16) not null,
    `primary` boolean not null,
    person_id int not null,
    primary key (id),
    foreign key (person_id) references person(id)
);

create index phone_person_id on phone(person_id);

create table address (
    id int not null auto_increment,
    street varchar(32) not null,
    city varchar(32) not null,
    state varchar(32),
    zip char(10),
    `primary` boolean not null,
    person_id int not null,
    primary key (id),
    foreign key (person_id) references person(id)
);

create index address_person_id on address(person_id);

create table role (
    id varchar(10) not null,
    primary key (id)
);

insert into role(id) values ('owner');
insert into role(id) values ('admin');
insert into role(id) values ('writer');
insert into role(id) values ('editor');
insert into role(id) values ('reviewer');

create table website_role (
    id int not null auto_increment,
    role_id varchar(10) not null,
    website_id int not null,
    developer_id int not null,
    primary key (id),
    foreign key (role_id) references role(id),
    foreign key (website_id) references website(id),
    foreign key (developer_id) references developer(id)
);

create index website_role_website_id on website_role(website_id);
create index website_role_developer_id on website_role(developer_id);

create table page_role (
    id int not null auto_increment,
    role_id varchar(10) not null,
    page_id int not null,
    developer_id int not null,
    primary key (id),
    foreign key (role_id) references role(id),
    foreign key (page_id) references page(id),
    foreign key (developer_id) references developer(id)
);

create index page_role_page_id on page_role(page_id);
create index page_role_developer_id on page_role(developer_id);

create table privilege (
    id varchar(10) not null,
    primary key (id)
);

insert into privilege(id) values ('create');
insert into privilege(id) values ('read');
insert into privilege(id) values ('update');
insert into privilege(id) values ('delete');

create table website_privilege (
    id int not null auto_increment,
    website_id int not null,
    developer_id int not null,
    privilege_id varchar(10),
    primary key (id),
    foreign key (website_id) references website(id),
    foreign key (developer_id) references developer(id),
    foreign key (privilege_id) references privilege(id)
);

create index website_privilege_developer_id on website_privilege(developer_id);
create index website_privilege_website_id on website_privilege(developer_id);

create table page_privilege (
    id int not null auto_increment,
    page_id int not null,
    developer_id int not null,
    privilege_id varchar(10) not null,
    primary key (id),
    foreign key (page_id) references page(id),
    foreign key (developer_id) references developer(id),
    foreign key (privilege_id) references privilege(id)
);

create index page_privilege_developer_id on page_privilege(developer_id);
create index page_privilege_page_id on page_privilege(page_id);