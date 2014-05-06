/**
 * UserDao.java
 *
 * $Author: ialexan $
 * $Date: 2012-09-27 14:29:19 -0700 (Thu, 27 Sep 2012) $
 */
package bookpub.model.dao;

import bookpub.model.User;

public interface UserDao {

    User getUser( Long id );

    User getUser( String email );

    User saveUser( User user );

}
