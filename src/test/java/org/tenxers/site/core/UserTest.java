package org.tenxers.site.core;

import org.junit.Test;

import java.util.LongSummaryStatistics;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 01:41
 */
public class UserTest {

    private static final Optional<Long> legitId = Optional.of(1L);

    @Test (expected = IllegalArgumentException.class)
    public void testIdCannotBeNull()
    {
        new User(null, "OK", "OK", "OK");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIdMustBeGreaterThanZero()
    {
        new User(Optional.of(0L), "OK", "OK", "OK");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPasswordHashLettersAndNumbersOnly()
    {
        new User(legitId, "OK", "OK", "@:|$%");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordHashNotNull() {
        new User(legitId, "ok", "ok", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondNameNonAtoZatoz()
    {
        new User(legitId, "fine", "@2342", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFirstNameNonAtoZatoz()
    {
        new User(legitId, "@:", "Fine", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullSurname() {
        new User(legitId, "Ed", null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFirstName() {
        new User(legitId, null, "Lewis", "");
    }

    @Test
    public void testHasId()
    {
        User u = new User(legitId, "Ed", "Lewis", "ABC99FF234");
        User x = new User(Optional.empty(), "Ed", "Lewis", "ABC99FF234");

        assertTrue(u.hasUserId());
        assertFalse(x.hasUserId());
    }

    @Test
    public void testNormalConstructor() {
        User u = new User(legitId, "Ed", "Lewis", "ABC99FF234");
        assertEquals("Ed", u.getFirstName());
        assertEquals("Lewis", u.getSecondName());
        assertEquals("ABC99FF234", u.getPasswordHash());
        assertEquals(legitId.get(), u.getId().get());

        User x = new User(Optional.empty(), "Ed", "Lewis", "ABC99FF234");
        assertEquals(Optional.empty(), x.getId());
    }

}