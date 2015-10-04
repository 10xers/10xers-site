package org.tenxers.site.web;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import testcats.FastTests;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 27/07/2015 18:41
 */
@Category(FastTests.class)
public class BlogControllerTest {

    @Test
    @Ignore("test needs rethinking because it now hits DB")
    public void testBlog() throws Exception {
        assertEquals("blog", new BlogController().blog(Mockito.mock(Model.class)));
    }
    
}