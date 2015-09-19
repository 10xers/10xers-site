package org.tenxers.site.core;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.tenxers.site.core.models.Password;
import org.tenxers.site.core.models.User;
import testcats.FastTests;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 01:41
 */
@Category(FastTests.class)
public class UserTest {

    private static final Password legitPassword = PasswordMaker.make("OK");
    private static final String username = "EdLewis";


    @Test (expected = IllegalArgumentException.class)
    public void testUsernameCannotBeBlank() {
        new User(" ", legitPassword, "OK", "OK");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testUsernameCannotBeNull() {
        new User(null, legitPassword, "OK", "OK");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordNotNull() {
        new User(username, null, "ok", "ok");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondNameNonAtoZatoz()
    {
        new User(username, legitPassword, "fine", "@2342");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFirstNameNonAtoZatoz()
    {
        new User(username, legitPassword, "@:", "Fine");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSurname() {
        new User(username, legitPassword, "Ed", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFirstName() {
        new User(username, legitPassword, null, "Lewis");
    }

    @Test
    public void testNormalConstructor() {
        User u = new User(username, legitPassword, "Ed", "Lewis");
        assertEquals("Ed", u.getFirstName());
        assertEquals("Lewis", u.getSecondName());
        assertEquals(username, u.getUsername());
        assertEquals(legitPassword.getHash(), u.getPassword().getHash());

        User x = new User(username, legitPassword, "Ed", "Lewis");
        assertEquals(0, x.getId());
    }

}