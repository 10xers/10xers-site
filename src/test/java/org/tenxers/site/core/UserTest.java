package org.tenxers.site.core;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 01:41
 */
public class UserTest {

    private static final Optional<Long> legitId = Optional.of(1L);
    private static final Password legitPassword = PasswordMaker.make("OK");

    @Test (expected = IllegalArgumentException.class)
    public void testIdCannotBeNull()
    {
        new User(null, "OK", "OK", legitPassword);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdMustBeGreaterThanZero()
    {
        new User(Optional.of(0L), "OK", "OK", legitPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordNotNull() {
        new User(legitId, "ok", "ok", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondNameNonAtoZatoz()
    {
        new User(legitId, "fine", "@2342", legitPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFirstNameNonAtoZatoz()
    {
        new User(legitId, "@:", "Fine", legitPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSurname() {
        new User(legitId, "Ed", null, legitPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFirstName() {
        new User(legitId, null, "Lewis", legitPassword);
    }

    @Test
    public void testHasId()
    {
        User u = new User(legitId, "Ed", "Lewis", legitPassword);
        User x = new User(Optional.empty(), "Ed", "Lewis", legitPassword);

        assertTrue(u.hasUserId());
        assertFalse(x.hasUserId());
    }

    @Test
    public void testNormalConstructor() {
        User u = new User(legitId, "Ed", "Lewis", legitPassword);
        assertEquals("Ed", u.getFirstName());
        assertEquals("Lewis", u.getSecondName());
        assertEquals(legitPassword.getHash(), u.getPassword().getHash());
        assertEquals(legitId.get(), u.getId().get());

        User x = new User(Optional.empty(), "Ed", "Lewis", legitPassword);
        assertEquals(Optional.empty(), x.getId());
    }

}