/**
 * BookDao.java
 *
 * $Author: cysun $
 * $Date: 2012-09-18 14:57:24 -0700 (Tue, 18 Sep 2012) $
 */
package bookpub.model.dao;

import java.util.List;

import bookpub.model.Book;

public interface BookDao {

    Book getBook( Long id );

    List<Book> getBooks();

    Book saveBook( Book book );

}
