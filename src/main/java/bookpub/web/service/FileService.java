/**
 * FileServlice.java
 * 
 * $Author: ialexan $
 * $Date: 2012-12-04 20:16:56 -0800 (Tue, 04 Dec 2012) $
 */
package bookpub.web.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bookpub.model.dao.FileDao;
import bookpub.util.FileIO;

@Controller
public class FileService {

    @Autowired
    FileDao fileDao;

    @Autowired
    FileIO fileIO;

    @RequestMapping("/service/file/{id}")
    public String file( @PathVariable Long id, HttpServletResponse response )
        throws IOException
    {
        response.setContentType( "image/png" );
        fileIO.copy( fileDao.getFile( id ), response.getOutputStream() );
        return null;
    }
    
    @RequestMapping("/service/epubFile/{id}")
    public String epubFile( @PathVariable Long id, HttpServletResponse response )
        throws IOException
    {
        response.setContentType( "epub/epub" );
        fileIO.epubCopy( fileDao.getFile( id ), response.getOutputStream() );
        return null;
    }

}
