package org.tenxers.site.web.helpers;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tenxers.site.Application;
import org.tenxers.site.core.PasswordMaker;
import org.tenxers.site.core.models.Password;
import org.tenxers.site.core.models.User;
import org.tenxers.site.core.repositories.UserRepository;
import testcats.SlowTests;

import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.junit.Assert.*;
import org.junit.Ignore;
import static org.mockito.Mockito.*;
import static org.tenxers.site.web.helpers.Helpers.addLoginToSession;
import static org.tenxers.site.web.helpers.Helpers.isLoggedIn;

/**
 * site / Ed
 * 06/08/2015 18:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Category(SlowTests.class)
@Ignore
public class HelpersTest {

    @Autowired
    UserRepository userRepository;

    @Test (expected = IllegalStateException.class)
    public void testIsLoggedInWrongObject() throws Exception {
        HttpSession m = mock(HttpSession.class);
        when(m.getAttribute("loggedInUser")).thenReturn(new Object());
        isLoggedIn(m);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIsLoggedNull() throws Exception {
        isLoggedIn(null);
    }

    @Test
    public void testIsLoggedIn() throws Exception {

        HttpSession badSessionEmpty = mock(HttpSession.class);
        when(badSessionEmpty.getAttribute("loggedInUser")).thenReturn(null);
        assertFalse(isLoggedIn(badSessionEmpty));
        verify(badSessionEmpty, times(1)).getAttribute("loggedInUser");

        HttpSession goodSesssion = mock(HttpSession.class);
        when(goodSesssion.getAttribute("loggedInUser")).thenReturn(new User("edlewis", PasswordMaker.make("abcd"), "Ed", "Lewis"));
        assertTrue(isLoggedIn(goodSesssion));
        verify(goodSesssion, times(1)).getAttribute("loggedInUser");

    }


    @Test
    public void testTryLogin() throws Exception {
        UserRepository m = userRepository;
        Password valid = PasswordMaker.make("abc");
        User permitted = new User("edlewis", valid, "Ed", "Lewis");
        m.save(permitted);

        assertEquals(Optional.empty(), Helpers.tryLogin("EdLewis", "999", m)); // invalid password

        Optional<User> loggedIn = Helpers.tryLogin("edlewis", valid.getHash(), m); // ok

        assertTrue(loggedIn.isPresent());
        assertEquals(permitted.getId(), loggedIn.get().getId());

        assertEquals(Optional.empty(), Helpers.tryLogin("asdfasdfasdfasdf", "abc", m)); // ok
    }

    @Test
    public void testAddLoginToSession() throws Exception {
        HttpSession session = mock(HttpSession.class);
        User u =  new User("edlewis", PasswordMaker.make("abc"), "Ed", "Lewis");
        addLoginToSession(session, u);
        verify(session, times(1)).setAttribute("loggedInUser", u);

    }
}