/**
 * Chapter.java
 * 
 * $Author: cysun $
 * $Date: 2012-09-19 12:07:52 -0700 (Wed, 19 Sep 2012) $
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

@Entity
@Table(name = "chapters")
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private int number;

    private String title;

    private String description;

    private String content;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "date_published")
    private Date datePublished;

    public Chapter()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook( Book book )
    {
        this.book = book;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber( int number )
    {
        this.number = number;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content = content;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated( Date dateCreated )
    {
        this.dateCreated = dateCreated;
    }

    public Date getDatePublished()
    {
        return datePublished;
    }

    public void setDatePublished( Date datePublished )
    {
        this.datePublished = datePublished;
    }

}
