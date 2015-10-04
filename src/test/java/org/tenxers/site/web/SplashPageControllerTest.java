package org.tenxers.site.web;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import testcats.FastTests;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 27/07/2015 18:37
 */
@Category(FastTests.class)
public class SplashPageControllerTest {

    @Test
    @Ignore("this can't work how it is at the moment")
    public void testSplash() throws Exception {
        Model m = Mockito.mock(Model.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        assertEquals("splash", new SplashPageController().splash(session, m));
    }
}