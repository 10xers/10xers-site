package org.tenxers.site.web;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 27/07/2015 18:37
 */
public class SplashPageControllerTest {

    @Test
    public void testSplash() throws Exception {
        Model m = Mockito.mock(Model.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        assertEquals("splash", new SplashPageController().splash(session, m));
    }
}