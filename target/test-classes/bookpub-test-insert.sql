-- DB Initialization Script for Bookpub
--
-- $Author: ialexan $
-- $Date: 2012-12-04 20:16:56 -0800 (Tue, 04 Dec 2012) $

set client_min_messages=WARNING;

insert into users (id, email, password, last_name, first_name ) values
    (2000, 'jdoe@localhost.localdomain', md5('abcd'), 'Doe', 'John');          

insert into files (id, name, owner_id) values (1000100, 'cover 0', 1001);
insert into files (id, name, owner_id) values (1000101, 'cover 1', 1001);
insert into files (id, name, owner_id) values (1000102, 'cover 2', 1001);
insert into files (id, name, owner_id) values (1000103, 'cover 3', 1001);
insert into files (id, name, owner_id) values (1000104, 'cover 4', 1001);
insert into files (id, name, owner_id) values (1000105, 'cover 5', 1001);
insert into files (id, name, owner_id) values (1000106, 'cover 6', 1001);
insert into files (id, name, owner_id) values (1000107, 'cover 7', 1001);
insert into files (id, name, owner_id) values (1000108, 'cover 8', 1001);
insert into files (id, name, owner_id) values (1000109, 'cover 9', 1001);

insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000100, 'SQL for Beginners', 'SQL book for beginners and starters.', 1000100, current_timestamp, 2000);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000101, 'JAVA for Beginners', 'Java book for beginners and starters.', 1000101, current_timestamp, 1999);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000102, 'PHP for Beginners', 'PHP book for beginners and starters.', 1000102, current_timestamp, 2500);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000103, 'Android for Beginners', 'Android book for beginners and starters.', 1000103, current_timestamp, 4999);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000104, 'iOS for Beginners', 'iOS book for beginners and starters.', 1000104, current_timestamp, 4999);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000105, 'Python for Beginners', 'Python book for beginners and starters.', 1000105, current_timestamp, 3000);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000106, 'Perl for Beginners', 'Perl book for beginners and starters.', 1000106, current_timestamp, 2000);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000107, 'Hibernate for Beginners', 'Hibernate book for beginners and starters.', 1000107, current_timestamp, 3000);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000108, 'Spring for Beginners', 'Spring book for beginners and starters.', 1000108, current_timestamp, 4000);
insert into books (id, title, description, cover_thumbnail_id, date_published, price ) values
    (1000109, 'HTML for Beginners', 'HTML book for beginners and starters.', 1000109, current_timestamp, 999);
    
insert into chapters (id, book_id, number, date_published) values
    (1000100, 1000100, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000101, 1000101, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000102, 1000102, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000103, 1000103, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000104, 1000104, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000105, 1000105, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000106, 1000106, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000107, 1000107, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000108, 1000108, 0, current_timestamp);
insert into chapters (id, book_id, number, date_published) values
    (1000109, 1000109, 0, current_timestamp);

insert into book_authors (book_id, author_id, author_order) values (1000100, 1000, 0);
insert into book_authors (book_id, author_id, author_order) values (1000100, 1001, 1);
insert into book_authors (book_id, author_id, author_order) values (1000101, 1001, 0);
insert into book_authors (book_id, author_id, author_order) values (1000102, 1000, 0);
insert into book_authors (book_id, author_id, author_order) values (1000103, 1001, 0);
insert into book_authors (book_id, author_id, author_order) values (1000104, 1000, 0);
insert into book_authors (book_id, author_id, author_order) values (1000105, 1001, 0);
insert into book_authors (book_id, author_id, author_order) values (1000106, 1000, 0);
insert into book_authors (book_id, author_id, author_order) values (1000107, 1001, 0);
insert into book_authors (book_id, author_id, author_order) values (1000108, 1000, 0);
insert into book_authors (book_id, author_id, author_order) values (1000109, 1001, 0);

insert into purchases (id, user_id, book_id, price) values (1000200, 1000, 1000101, 1999);
insert into purchases (id, user_id, book_id, price) values (1000201, 1001, 1000102, 2500);
insert into purchases (id, user_id, book_id, price) values (1000202, 2000, 1000100, 2000);
insert into purchases (id, user_id, book_id, price) values (1000203, 2000, 1000101, 1999);
insert into purchases (id, user_id, book_id, price) values (1000204, 2000, 1000102, 2500);
