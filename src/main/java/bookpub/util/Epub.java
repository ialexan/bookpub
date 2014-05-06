/*
 * Ishag Alexanian
 * Epub deals with creating and reading epub files
 * 
 * October 25 2012
 */

package bookpub.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import bookpub.model.User;

import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.viewer.Viewer;
import nl.siegmann.epublib.domain.Resource;

//import nl.siegmann.epublib.viewer.*;

@Component
public class Epub {

    @Value("#{applicationProperties['file.dir']}")
    private String fileDir;

    public Epub()
    {

    }

    public void createBook( bookpub.model.Book book )
    {

        try
        {
            // Create new Book
            Book ebook = new Book();

            // Set the title
            ebook.getMetadata().addTitle( book.getTitle() );

            // Create EpubWriter
            EpubWriter epubWriter = new EpubWriter();

            // Add Authors
            for( User author : book.getAuthors() )
                ebook.getMetadata().addAuthor(
                    new Author( author.getFirstName(), author.getLastName() ) );

            // Set cover image
            // java.io.File onDiskCoverPage = new java.io.File( fileDir ,
            // book.getCoverThumbnail().getId().toString() );
            // ebook.getMetadata().setCoverImage( new Resource( new
            // FileInputStream(onDiskCoverPage),
            // book.getCoverThumbnail().getType().toString() ));

            // Add Chapters
            // ebook.addSection(chapter.getTitle(), new
            // Resource(chapter.getContent().getBytes(), "chapter1.html") );

            // Write the Book as Epub
            String fileId = book.getId().toString() + ".epub";
            java.io.File diskFile = new java.io.File( fileDir, fileId );

            epubWriter.write( ebook, new FileOutputStream( diskFile ) );

        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

    }

    public void readBook( bookpub.model.Book book )
    {
        // read epub file
        EpubReader epubReader = new EpubReader();
        String fileId = book.getId().toString() + ".epub";
        java.io.File diskFile = new java.io.File( fileDir, fileId );

        Book ebook = null;
        try
        {
            ebook = epubReader.readEpub( new FileInputStream( diskFile ) );
        }
        catch( FileNotFoundException e )
        {
            System.out.print( "Ebook Not Found" );
        }
        catch( IOException e )
        {
            System.out.print( "Input/Output Problem" );
        }

        // print the first title
        List<String> titles = ebook.getMetadata().getTitles();
        System.out.println( "book title:"
            + (titles.isEmpty() ? "book has no title" : titles.get( 0 )) );

        Viewer view = new Viewer( ebook );
    }

    public void updateBook( bookpub.model.Chapter chapter )
    {

        // read epub file
        EpubReader epubReader = new EpubReader();
        String fileId = chapter.getBook().getId().toString() + ".epub";
        java.io.File diskFile = new java.io.File( fileDir, fileId );

        Book ebook = null;
        try
        {
            ebook = epubReader.readEpub( new FileInputStream( diskFile ) );

            // Add Chapters
            ebook.addSection( chapter.getTitle(), new Resource(
                chapter.getContent().getBytes(),
                chapter.getTitle() + ".html" ) );

            // Create EpubWriter
            EpubWriter epubWriter = new EpubWriter();

            // Write the Book as Epub
            epubWriter.write( ebook, new FileOutputStream( diskFile ) );

        }
        catch( FileNotFoundException e )
        {
            System.out.print( "Ebook Not Found" );
        }
        catch( IOException e )
        {
            System.out.print( "Input/Output Problem" );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

    }

}
