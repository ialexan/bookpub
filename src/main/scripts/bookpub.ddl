
    create table authorities (
        user_id int8 not null,
        roles varchar(255)
    );

    create table book_authors (
        book_id int8 not null,
        author_id int8 not null,
        author_order int4 not null,
        primary key (book_id, author_order)
    );

    create table books (
        id int8 not null,
        date_created timestamp not null,
        date_published timestamp,
        description varchar(255),
        price int4 not null,
        title varchar(255) not null,
        cover_id int8,
        primary key (id)
    );

    create table chapters (
        id int8 not null,
        content varchar(255),
        date_created timestamp not null,
        date_published timestamp,
        description varchar(255),
        number int4 not null,
        title varchar(255),
        book_id int8 not null,
        primary key (id)
    );

    create table files (
        id int8 not null,
        date timestamp not null,
        deleted boolean not null,
        name varchar(255) not null,
        size int8,
        type varchar(255),
        owner_id int8 not null,
        primary key (id)
    );

    create table purchases (
        id int8 not null,
        date timestamp not null,
        price int4 not null,
        book_id int8 not null,
        user_id int8 not null,
        primary key (id)
    );

    create table users (
        id int8 not null,
        birthday timestamp,
        email varchar(255) not null unique,
        enabled boolean not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        primary key (id)
    );

    alter table authorities 
        add constraint FK2B0F13218E6B2859 
        foreign key (user_id) 
        references users;

    alter table book_authors 
        add constraint FKCBA3F8F29B026B99 
        foreign key (book_id) 
        references books;

    alter table book_authors 
        add constraint FKCBA3F8F2EF241A99 
        foreign key (author_id) 
        references users;

    alter table books 
        add constraint FK59922AA8231B29E 
        foreign key (cover_id) 
        references files;

    alter table chapters 
        add constraint FK55830DC69B026B99 
        foreign key (book_id) 
        references books;

    alter table files 
        add constraint FK5CEBA77FA51D871 
        foreign key (owner_id) 
        references users;

    alter table purchases 
        add constraint FK95379B929B026B99 
        foreign key (book_id) 
        references books;

    alter table purchases 
        add constraint FK95379B928E6B2859 
        foreign key (user_id) 
        references users;

    create sequence hibernate_sequence;
