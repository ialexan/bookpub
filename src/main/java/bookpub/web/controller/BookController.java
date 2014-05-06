/**
 * BookController.java
 * 
 * $Author: Ishag Alexanian $
 * $Date: Sun, 7 Nov 2012 $
 */

package bookpub.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import bookpub.model.Book;
import bookpub.model.Chapter;
import bookpub.model.File;
import bookpub.model.Purchase;
import bookpub.model.User;
import bookpub.model.dao.BookDao;
import bookpub.model.dao.ChapterDao;
import bookpub.model.dao.FileDao;
import bookpub.model.dao.PurchaseDao;
import bookpub.security.SecurityUtils;
import bookpub.util.Epub;
import bookpub.util.FileIO;

@Controller
public class BookController {

    @Autowired
    private ChapterDao chapterDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileIO fileIO;

    @Autowired
    private Epub epub;

    // -------------------------------------------------------------------------------

    // View all Books
    @RequestMapping("/author/viewBooks")
    public String viewBooks( ModelMap models )
    {
        models.put( "books", bookDao.getBooks() );
        return "author/viewBooks";
    }

    // View a specific Book
    @RequestMapping("/author/viewBook")
    public String viewBook( @RequestParam Long bookId, ModelMap models )
    {
        Book book = bookDao.getBook( bookId );

        String userIsAuthor = "n";

        for( User user : book.getAuthors() )
        {
            if( SecurityUtils.getUser().getId().equals( user.getId() ) )
                userIsAuthor = "y";
        }

        for( Chapter chapter : book.getChapters() )
            System.out.println( chapter.getId() );

        // epub.readBook( book );

        models.put( "userIsAuthor", userIsAuthor );
        models.put( "book", book );
        return "author/viewBook";
    }
    

    // View purchased books that is in the library
    @RequestMapping("/author/library")
    public String library( ModelMap models )
    {
        User user = SecurityUtils.getUser();
        List<Purchase> purchases = purchaseDao.getPurchases( user );
        List<Book> books = new ArrayList<Book>();
        for( Purchase purchase : purchases )
            books.add( purchase.getBook() );

        models.put( "books", books );
        return "author/library";
    }

    // View written Books
    @RequestMapping("/author/writtenBooks")
    public String writtenBooks( ModelMap models )
    {
        User user = SecurityUtils.getUser();

        List<Book> allBooks = bookDao.getBooks();

        List<Book> books = new ArrayList<Book>();

        for( Book book : allBooks )
        {
            for( User author : book.getAuthors() )
            {
                if( user.getId().equals( author.getId() ) )
                {
                    books.add( book );
                }
            }
        }

        models.put( "books", books );
        return "author/writtenBooks";
    }

    // -------------------------------------------------------------------------------

    // Adding a Book
    @RequestMapping(value = "/author/addBook", method = RequestMethod.GET)
    public String addBook( ModelMap models )
    {
        Book book = new Book();

        models.addAttribute( "book", book );
        return "author/addBook";
    }

    @RequestMapping(value = "/author/addBook", method = RequestMethod.POST)
    public String addBook( @ModelAttribute Book book,
        @RequestParam(required = false) MultipartFile uploadedFile,
        SessionStatus sessionStatus ) throws IOException
    {
        BufferedImage originalImage = ImageIO.read( uploadedFile.getInputStream() );

        User user = SecurityUtils.getUser();

        book.setDateCreated( new Date() ); // Get the current date when adding
                                           // the book
        book.getAuthors().add( user );

        // Adding a CoverPage Image
        if( uploadedFile == null || uploadedFile.isEmpty() )
            return "redirect:/author/addBook";

        File file = new File();
        String fileName = uploadedFile.getOriginalFilename();
        file.setName( fileName );
        file.setOwner( user );
        file.setType( uploadedFile.getContentType() );
        file.setSize( uploadedFile.getSize() );
        file.setDate( new Date() );
        file = fileDao.saveFile( file );

        fileIO.save( file, uploadedFile );

        book.setCover( file );

        // Generating Thumbnail
        int newWidth = 80;
        int newHeight = 100;

        BufferedImage thumbnail = Scalr.resize( originalImage,
            Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, newWidth,
            newHeight );

        File coverThumbnailFile = new File();

        coverThumbnailFile.setName( file.getId() + ".png" );
        coverThumbnailFile.setOwner( user );
        coverThumbnailFile.setType( "png" );
        // coverThumbnailFile.setSize( thumbnail.getSize() );
        coverThumbnailFile.setDate( new Date() );
        coverThumbnailFile = fileDao.saveFile( coverThumbnailFile );

        fileIO.SaveThumbnail( coverThumbnailFile, thumbnail );

        book.setCoverThumbnail( coverThumbnailFile );

        book = bookDao.saveBook( book );

        epub.createBook( book );
        epub.readBook( book );

        return "redirect:/author/writtenBooks";
    }

    // -----------------------------------------------------------------------------------

    // Adding a chapter
    @RequestMapping(value = "/author/addChapter", method = RequestMethod.GET)
    public String addChapter( @RequestParam Long bookId, ModelMap models )
    {
        // From the book id get the book
        Book book = bookDao.getBook( bookId );

        Chapter chapter = new Chapter();
        chapter.setBook( book );
        book.getChapters().add( chapter );

        models.addAttribute( "chapter", chapter );
        return "author/addChapter";
    }

    @RequestMapping(value = "/author/addChapter", method = RequestMethod.POST)
    public String addChapter( @ModelAttribute Chapter chapter,
        @RequestParam Long bookId )
    {
        Book book = bookDao.getBook( bookId );
        chapter.setBook( book );

        chapter.setDateCreated( new Date() ); // Get the current date when
                                              // adding the chapter

        chapter = chapterDao.saveChapter( chapter );

        // Create Epub Ebook
        epub.updateBook( chapter );
        epub.readBook( book );

        return "redirect:/author/viewBook?bookId="
            + chapter.getBook().getId().toString();
    }

    // -------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------

}
