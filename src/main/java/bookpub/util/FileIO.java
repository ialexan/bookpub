/*
 * This file is part of the CSNetwork Services (CSNS) project.
 * 
 * Copyright 2012, Chengyu Sun (csun@calstatela.edu).
 * 
 * CSNS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * CSNS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with CSNS. If not, see http://www.gnu.org/licenses/agpl.html.
 */
package bookpub.util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import bookpub.model.File;

@Component
public class FileIO {

    @Value("#{applicationProperties['file.dir']}")
    private String fileDir;

    private static final Logger logger = LoggerFactory.getLogger( FileIO.class );

    public FileIO()
    {
    }

    public void save( File file, MultipartFile uploadedFile )
    {
        String fileId = file.getId().toString();
        java.io.File diskFile = new java.io.File( fileDir, fileId );

        try
        {
            uploadedFile.transferTo( diskFile );

        }
        catch( Exception e )
        {
            logger.error( "Failed to save uploaded file", e );
        }
    }

    public void SaveThumbnail( File file, BufferedImage thumbnail )
    {
        String fileId = file.getId().toString();
        java.io.File diskFile = new java.io.File( fileDir, fileId );

        try
        {
            ImageIO.write( thumbnail, "png", diskFile );
        }
        catch( Exception e )
        {
            logger.error( "Failed to save thumbnail", e );
        }
    }

    public void copy( InputStream in, OutputStream out )
    {
        byte[] buffer = new byte[4096];
        int bytesRead;
        try
        {
            while( (bytesRead = in.read( buffer )) != -1 )
                out.write( buffer, 0, bytesRead );
        }
        catch( IOException e )
        {
            logger.error( "Failed to copy input to output", e );
        }
    }

    public void copy( File file, OutputStream out )
    {
        try
        {
            String fileId = file.getId().toString();
            java.io.File diskFile = new java.io.File( fileDir, fileId );
            FileInputStream in = new FileInputStream( diskFile );
            copy( in, out );
            in.close();
        }
        catch( Exception e )
        {
            logger.error( "Failed to copy file to output", e );
        }
    }

    public void epubCopy( File file, OutputStream out )
    {
        try
        {
            String fileId = file.getId().toString() + ".epub";
            java.io.File diskFile = new java.io.File( fileDir, fileId );
            FileInputStream in = new FileInputStream( diskFile );
            copy( in, out );
            in.close();
        }
        catch( Exception e )
        {
            logger.error( "Failed to copy file to output", e );
        }
    }

}
