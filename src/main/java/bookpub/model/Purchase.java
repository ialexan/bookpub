/**
 * Purcahse.java
 * 
 * $Author$
 * $Date$
 */
package bookpub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "purchases")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private Date date;

    public Purchase()
    {
    }

    public Purchase( User user, Book book )
    {
        this.user = user;
        this.book = book;
        this.price = book.getPrice();
        this.date = new Date();
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook( Book book )
    {
        this.book = book;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice( int price )
    {
        this.price = price;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

}
