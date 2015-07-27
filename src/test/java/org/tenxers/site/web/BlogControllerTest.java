package org.tenxers.site.web;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 27/07/2015 18:41
 */
public class BlogControllerTest {

    @Test
    public void testBlog() throws Exception {
        assertEquals("blog", new BlogController().blog());
    }
    
}