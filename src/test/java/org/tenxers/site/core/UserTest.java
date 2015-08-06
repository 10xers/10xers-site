package org.tenxers.site.core;

import org.junit.Test;
import org.tenxers.site.core.models.Password;
import org.tenxers.site.core.models.User;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 01:41
 */
public class UserTest {

    private static final Optional<Long> legitId = Optional.of(1L);
    private static final Password legitPassword = PasswordMaker.make("OK");
    private static final String username = "EdLewis";


    @Test (expected = IllegalArgumentException.class)
    public void testUsernameCannotBeBlank() {
        new User(Optional.empty(), " ", legitPassword, "OK", "OK");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testUsernameCannotBeNull() {
        new User(Optional.empty(), null, legitPassword, "OK", "OK");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdCannotBeNull()
    {
        new User(null, username, legitPassword, "OK", "OK");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdMustBeGreaterThanZero()
    {
        new User(Optional.of(0L), username, legitPassword, "OK", "OK");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordNotNull() {
        new User(legitId, username, null, "ok", "ok");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondNameNonAtoZatoz()
    {
        new User(legitId, username, legitPassword, "fine", "@2342");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFirstNameNonAtoZatoz()
    {
        new User(legitId, username, legitPassword, "@:", "Fine");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSurname() {
        new User(legitId, username, legitPassword, "Ed", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFirstName() {
        new User(legitId, username, legitPassword, null, "Lewis");
    }

    @Test
    public void testHasId()
    {
        User u = new User(legitId, username, legitPassword, "Ed", "Lewis");
        User x = new User(Optional.empty(), username, legitPassword, "Ed", "Lewis");

        assertTrue(u.hasUserId());
        assertFalse(x.hasUserId());
    }

    @Test
    public void testNormalConstructor() {
        User u = new User(legitId, username, legitPassword, "Ed", "Lewis");
        assertEquals("Ed", u.getFirstName());
        assertEquals("Lewis", u.getSecondName());
        assertEquals(username, u.getUsername());
        assertEquals(legitPassword.getHash(), u.getPassword().getHash());
        assertEquals(legitId.get(), u.getId().get());

        User x = new User(Optional.empty(), username, legitPassword, "Ed", "Lewis");
        assertEquals(Optional.empty(), x.getId());
    }

}