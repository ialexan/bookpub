/**
 * ChapterDao.java
 *
 * $Author: Ishag Alexanian $
 * $Date: (Sun, 7 Nov 2012) $
 */

package bookpub.model.dao;

import java.util.List;

import bookpub.model.Chapter;

public interface ChapterDao {

    Chapter getChapter( Long id );

    List<Chapter> getChapters();

    Chapter saveChapter( Chapter chapter );

}
