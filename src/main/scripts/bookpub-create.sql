-- DB Creation Script for Bookpub
--
-- $Author: ialexan $
-- $Date: 2012-11-27 14:16:39 -0800 (Tue, 27 Nov 2012) $


set client_min_messages=WARNING;

create sequence hibernate_sequence minvalue 2000000;

-----------
-- users --
-----------

create table users (
    id          bigint primary key,
    email       varchar(255) not null unique,
    password    varchar(255) not null,
    last_name   varchar(255) not null,
    first_name  varchar(255) not null,
    birthday    date,
    enabled     boolean not null default 't'
);

create table authorities (
    user_id bigint not null references users(id),
    role    varchar(255),
  unique (user_id, role)
);

-- for remember-me service
create table persistent_logins (
    series      varchar(64) primary key,
    username    varchar(64) not null,
    token       varchar(64) not null,
    last_used   timestamp not null
);

insert into users (id, email, password, last_name, first_name ) values
    (1000, 'csun@calstatela.edu', md5('abcd'), 'Sun', 'Chengyu');

insert into authorities values (1000, 'ROLE_ADMIN');
insert into authorities values (1000, 'ROLE_AUTHOR');

insert into users (id, email, password, last_name, first_name ) values
    (1001, 'ishag@localhost.localdomain', md5('abcd'), 'Alexanian', 'Ishag');

insert into authorities values (1001, 'ROLE_ADMIN');
insert into authorities values (1001, 'ROLE_AUTHOR');

-----------
-- files --
-----------

create table files (
    id          bigint primary key,
    name        varchar(255) not null,
    type        varchar(255),
    size        bigint,
    date        timestamp not null default current_timestamp,
    owner_id    bigint not null references users(id),
    deleted     boolean not null default 'f'
);

-----------
-- books --
-----------

create table books (
    id              bigint primary key,
    title           varchar(255) not null,
    description     varchar(5000),
    cover_id        bigint references files(id),
    cover_thumbnail_id       bigint references files(id), 
    date_created    timestamp not null default current_timestamp,
    date_published  timestamp,
    price           integer not null check( price >= 0 )
);

create table book_authors (
    book_id         bigint not null references books(id),
    author_id       bigint not null references users(id),
    author_order    integer not null,
  primary key (book_id, author_order)
);

create table chapters (
    id              bigint primary key,
    book_id         bigint not null references books(id),
    number          integer not null,
    title           varchar(255),
    description     varchar(5000),
    content         text,
    date_created    timestamp not null default current_timestamp,
    date_published  timestamp
);

---------------
-- purchases --
---------------

create table purchases (
    id      bigint primary key,
    user_id bigint not null references users(id),
    book_id bigint not null references books(id),
    price   integer not null check( price >= 0 ),
    date    timestamp not null default current_timestamp
);
