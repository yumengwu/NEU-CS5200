create table person (
  dtype varchar(32) not null,
  id int not null auto_increment,
  first_name varchar(16) not null,
  last_name varchar(16) not null,
  username varchar(16) not null,
  password varchar(16) not null,
  office varchar(32) default null,
  tenured boolean default null,
  grad_year int default null,
  scholarship long default null,
  primary key (id)
);

create unique index person_username_index on person(username);

create table course (
  id int not null auto_increment,
  label varchar(32) not null,
  author_id int not null,
  primary key (id),
  foreign key (author_id) references person(id)
);

create table section (
  id int not null auto_increment,
  title varchar(16) not null,
  seats int,
  course_id int not null,
  primary key (id),
  foreign key (course_id) references course(id)
);

create table enrollment (
  id int not null auto_increment,
  grade int,
  feedback varchar(256),
  student_id int not null,
  section_id int not null,
  primary key (id),
  foreign key (student_id) references person(id),
  foreign key (section_id) references section(id)
);