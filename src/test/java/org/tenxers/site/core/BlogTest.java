package org.tenxers.site.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tenxers.site.Application;
import org.tenxers.site.core.models.Blog;
import org.tenxers.site.core.models.Password;
import org.tenxers.site.core.models.User;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 01:41
 */
public class BlogTest {

    private static final String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mollis ante turpis, sollicitudin cursus ex suscipit sit amet. Donec volutpat consequat sapien, in imperdiet lacus ultricies eget. Mauris id aliquet nisl, in ultricies dui. Duis tristique, ex non lacinia lobortis, odio tellus aliquet nibh, ac cursus ligula mauris non justo. Etiam id hendrerit justo. Curabitur vehicula est at posuere vehicula. Mauris in massa at nibh condimentum efficitur sit amet eget nulla. Aenean nisi erat, congue ut sollicitudin non, rutrum in mi.";
    private static final String title = "Lorem ipsum dolor sit amet";
    private static final Password legitPassword = PasswordMaker.make("ABC123");
    private static final String username = "edlewis";

    private static final User legitAuthor = new User(username, legitPassword, "Ed", "Lewis");

    @Test(expected = IllegalArgumentException.class)
    public void testAuthorCannotBeNull()
    {
        new Blog(title, text, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTextCannotBeEmpty()
    {
        new Blog(title, "", legitAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTextCannotBeNull()
    {
        new Blog(title, null, legitAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTitleLengthGreaterThanZero()
    {
        new Blog("", text, legitAuthor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTitleCannotBeNull()
    {
        new Blog(null, text, legitAuthor);
    }

    @Test
    public void testSensibleConstructor()
    {
        Blog b = new Blog(title, text, legitAuthor);
        assertEquals(0, b.getId());
        assertEquals(title, b.getTitle());
        assertEquals(text, b.getText());
        assertEquals(legitAuthor, b.getAuthor());
    }

}