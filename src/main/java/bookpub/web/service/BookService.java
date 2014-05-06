/**
 * BookService.java
 * 
 * $Author: ialexan $
 * $Date: 2012-09-27 14:29:19 -0700 (Thu, 27 Sep 2012) $
 */
package bookpub.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bookpub.model.Book;
import bookpub.model.Purchase;
import bookpub.model.User;
import bookpub.model.dao.BookDao;
import bookpub.model.dao.PurchaseDao;
import bookpub.security.SecurityUtils;

@Controller
public class BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    PurchaseDao purchaseDao;

    @RequestMapping("/service/books")
    public String books( ModelMap models )
    {
        models.put( "books", bookDao.getBooks() );
        return "jsonView";
    }

    @RequestMapping("/service/book/{id}")
    public String book( @PathVariable Long id, ModelMap models )
    {
        models.put( "book", bookDao.getBook( id ) );
        return "jsonView";
    }

    @RequestMapping("/service/library")
    public String library( ModelMap models )
    {
        User user = SecurityUtils.getUser();
        List<Purchase> purchases = purchaseDao.getPurchases( user );
        List<Book> books = new ArrayList<Book>();
        for( Purchase purchase : purchases )
            books.add( purchase.getBook() );

        models.put( "books", books );
        return "jsonView";
    }

    @RequestMapping("/service/purchase/book/{bookId}")
    public String purchase( @PathVariable Long bookId, ModelMap models )
    {
        User user = SecurityUtils.getUser();
        Book book = bookDao.getBook( bookId );
        purchaseDao.savePurchase( new Purchase( user, book ) );

        models.put( "result", new ServiceResponse() );
        return "jsonView";
    }

}
