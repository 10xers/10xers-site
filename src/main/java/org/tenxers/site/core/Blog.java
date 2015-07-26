package org.tenxers.site.core;

import java.util.Optional;

/**
 * site / Ed
 * 26/07/2015 01:40
 */
public class Blog {

    private Optional<Long> id;
    private String title;
    private String text;
    private User author;

    public Blog(Optional<Long> id, String title, String text, User author) {
        setId(id);
        setTitle(title);
        setText(text);
        setAuthor(author);
    }

    public void setAuthor(User author)
    {
        if (author==null)
            throw new IllegalArgumentException("author cannot be null");

        if (!author.hasUserId())
            throw new IllegalArgumentException("author must have a user id");

        this.author = author;
    }

    public void setText(String text) {
        if (text==null)
            throw new IllegalArgumentException("text cannot be null");
        if (text.isEmpty())
            throw new IllegalArgumentException("text cannot be empty");

        this.text = text;
    }

    private void setTitle(String title) {
        if (title==null)
            throw new IllegalArgumentException("title cannot be null");

        if (title.isEmpty())
            throw new IllegalArgumentException("title cannot be empty");

        this.title = title;
    }

    private final void setId(Optional<Long> id)
    {
        if (id == null)
            throw new IllegalArgumentException("id cannot be null");

        if (id.isPresent() && id.get() <= 0)
            throw new IllegalArgumentException("id if present must be greater than zero");

        this.id = id;
    }

    public Optional<Long> getId() {
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
