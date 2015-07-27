package org.tenxers.site.web;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 27/07/2015 18:37
 */
public class SplashPageControllerTest {

    @Test
    public void testSplash() throws Exception {
        assertEquals("splash", new SplashPageController().splash());
    }
}