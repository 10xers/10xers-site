package org.tenxers.site.core.models;

import javax.persistence.*;
import java.util.Optional;

/**
 * site / Ed
 * 26/07/2015 01:40
 */
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name="author", referencedColumnName="id",nullable=false)
    private User author;

    protected Blog() {}

    public Blog( String title, String text, User author) {
        setTitle(title);
        setText(text);
        setAuthor(author);
    }

    public void setAuthor(User author)
    {
        if (author==null)
            throw new IllegalArgumentException("author cannot be null");

        this.author = author;
    }

    public void setText(String text) {
        if (text==null)
            throw new IllegalArgumentException("text cannot be null");
        if (text.isEmpty())
            throw new IllegalArgumentException("text cannot be empty");

        this.text = text;
    }

    public void setTitle(String title) {
        if (title==null)
            throw new IllegalArgumentException("title cannot be null");

        if (title.isEmpty())
            throw new IllegalArgumentException("title cannot be empty");

        this.title = title;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }
}
