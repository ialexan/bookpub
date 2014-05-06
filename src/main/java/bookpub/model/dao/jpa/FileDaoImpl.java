/**
 * FileDaoImpl.java
 * 
 * $Author: cysun $
 * $Date: 2012-09-18 14:57:24 -0700 (Tue, 18 Sep 2012) $
 */
package bookpub.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bookpub.model.File;
import bookpub.model.dao.FileDao;

@Repository
public class FileDaoImpl implements FileDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public File getFile( Long id )
    {
        return entityManager.find( File.class, id );
    }

    @Override
    @Transactional
    public File saveFile( File file )
    {
        return entityManager.merge( file );
    }

}
