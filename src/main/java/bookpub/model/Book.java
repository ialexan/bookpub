/**
 * Book.java
 * 
 * $Author: ialexan $
 * $Date: 2012-11-27 14:16:06 -0800 (Tue, 27 Nov 2012) $
 */
package bookpub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "cover_id")
    private File cover;

    @OneToOne
    @JoinColumn(name = "cover_thumbnail_id")
    private File coverThumbnail;

    @ManyToMany
    @JoinTable(name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    @OrderColumn(name = "author_order")
    private List<User> authors;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "date_published")
    private Date datePublished;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @OrderColumn(name = "number")
    private List<Chapter> chapters;

    @Column(nullable = false)
    private int price;

    public Book()
    {
        authors = new ArrayList<User>();
        chapters = new ArrayList<Chapter>();
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
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

    public File getCover()
    {
        return cover;
    }

    public void setCover( File cover )
    {
        this.cover = cover;
    }

    public File getCoverThumbnail()
    {
        return coverThumbnail;
    }

    public void setCoverThumbnail( File coverThumbnail )
    {
        this.coverThumbnail = coverThumbnail;
    }

    public List<User> getAuthors()
    {
        return authors;
    }

    public void setAuthors( List<User> authors )
    {
        this.authors = authors;
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

    public List<Chapter> getChapters()
    {
        return chapters;
    }

    public void setChapters( List<Chapter> chapters )
    {
        this.chapters = chapters;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice( int price )
    {
        this.price = price;
    }

}
