package org.tenxers.site.web;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 27/07/2015 18:41
 */
public class LoginControllerTest {

    @Test
    public void testAdmin() throws Exception {
        assertEquals("login", new LoginController().login());
    }
}