/**
 * BookDaoImpl.java
 *
 * $Author: cysun $
 * $Date: 2012-09-19 12:07:52 -0700 (Wed, 19 Sep 2012) $
 */
package bookpub.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bookpub.model.Book;
import bookpub.model.dao.BookDao;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book getBook( Long id )
    {
        return entityManager.find( Book.class, id );
    }

    @Override
    public List<Book> getBooks()
    {
        return entityManager.createQuery( "from Book order by id asc",
            Book.class ).getResultList();
    }

    @Override
    @Transactional
    public Book saveBook( Book book )
    {
        return entityManager.merge( book );
    }

}
