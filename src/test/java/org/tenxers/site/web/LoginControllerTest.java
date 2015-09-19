package org.tenxers.site.web;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import testcats.FastTests;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 27/07/2015 18:41
 */
@Category(FastTests.class)
public class LoginControllerTest {

    @Test
    public void testAdmin() throws Exception {
        assertEquals("login", new LoginController().login());
    }
}