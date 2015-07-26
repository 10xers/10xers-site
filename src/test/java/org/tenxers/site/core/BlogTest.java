package org.tenxers.site.core;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 01:41
 */
public class BlogTest {

    private static final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mollis ante turpis, sollicitudin cursus ex suscipit sit amet. Donec volutpat consequat sapien, in imperdiet lacus ultricies eget. Mauris id aliquet nisl, in ultricies dui. Duis tristique, ex non lacinia lobortis, odio tellus aliquet nibh, ac cursus ligula mauris non justo. Etiam id hendrerit justo. Curabitur vehicula est at posuere vehicula. Mauris in massa at nibh condimentum efficitur sit amet eget nulla. Aenean nisi erat, congue ut sollicitudin non, rutrum in mi.";
    private static final String title = "Lorem ipsum dolor sit amet";

    private static final User legitAuthor = new User(Optional.of(22L), "Ed", "Lewis", "ABC123");

    @Test(expected = IllegalArgumentException.class)
    public void testAuthorHasValidId()
    {
        new Blog(Optional.empty(), title, text, new User(Optional.empty(), "Ed", "Lewis", "ABC123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAuthorCannotBeNull()
    {
        new Blog(Optional.empty(), title, text, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTextCannotBeEmpty()
    {
        new Blog(Optional.empty(), title, "", legitAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTextCannotBeNull()
    {
        new Blog(Optional.empty(), title, null, legitAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTitleLengthGreaterThanZero()
    {
        new Blog(Optional.empty(), "", text, legitAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTitleCannotBeNull()
    {
        new Blog(Optional.empty(), null, text, legitAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIdMustBeGreaterThanZero()
    {
        new Blog(Optional.of(0L), title, text, legitAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIdCannotBeNull()
    {
        new Blog(null, title, text, legitAuthor);
    }

    @Test
    public void testSensibleConstructor()
    {
        Blog b = new Blog(Optional.empty(), title, text, legitAuthor);
        assertEquals(Optional.empty(), b.getId());
        assertEquals(title, b.getTitle());
        assertEquals(text, b.getText());
        assertEquals(legitAuthor, b.getAuthor());
    }

}