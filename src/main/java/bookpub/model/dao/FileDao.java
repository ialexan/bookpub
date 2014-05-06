/**
 * FileDao.java
 * 
 * $Author: cysun $
 * $Date: 2012-09-18 14:57:24 -0700 (Tue, 18 Sep 2012) $
 */
package bookpub.model.dao;

import bookpub.model.File;

public interface FileDao {

    File getFile( Long id );

    File saveFile( File file );

}
