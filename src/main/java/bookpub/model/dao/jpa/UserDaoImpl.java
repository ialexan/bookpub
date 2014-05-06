/**
 * UserDaoImpl.java
 *
 * $Author: ialexan $
 * $Date: 2012-09-27 14:29:19 -0700 (Thu, 27 Sep 2012) $
 */
package bookpub.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bookpub.model.User;
import bookpub.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser( Long id )
    {
        return entityManager.find( User.class, id );
    }

    @Override
    public User getUser( String email )
    {
        List<User> users = entityManager.createQuery(
            "from User where email = :email", User.class )
            .setParameter( "email", email )
            .getResultList();
        return users.size() == 0 ? null : users.get( 0 );
    }

    @Override
    @Transactional
    public User saveUser( User user )
    {
        return entityManager.merge( user );
    }

}
