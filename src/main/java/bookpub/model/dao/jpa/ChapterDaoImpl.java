/**
 * ChapterDaoImpl.java
 *
 * $Author: Ishag Alexanian $
 * $Date: (Sun, 7 Nov 2012) $
 */

package bookpub.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bookpub.model.Chapter;
import bookpub.model.dao.ChapterDao;

@Repository
public class ChapterDaoImpl implements ChapterDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Chapter getChapter( Long id )
    {
        return entityManager.find( Chapter.class, id );
    }

    @Override
    public List<Chapter> getChapters()
    {
        return entityManager.createQuery( "from Chapter", Chapter.class )
            .getResultList();
    }

    @Override
    @Transactional
    public Chapter saveChapter( Chapter chapter )
    {
        return entityManager.merge( chapter );
    }

}
