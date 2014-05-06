-- DB Removal Script for Bookpub
--
-- $Author: ialexan $
-- $Date: 2012-09-27 14:29:19 -0700 (Thu, 27 Sep 2012) $

set client_min_messages=WARNING;

drop sequence hibernate_sequence;

drop table purchases;

drop table chapters; 
drop table book_authors;
drop table books;

drop table files;

drop table persistent_logins;
drop table authorities;
drop table users;
