package org.tenxers.site.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.tenxers.site.core.models.Password;
import testcats.FastTests;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 17:37
 */
@Category(FastTests.class)
public class PasswordTest {

    private Password legit;

    @Before
    public void setUp() throws Exception {
        legit = new Password("SALT", "HASH", Password.HashType.SHA_256);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetSaltEmpty()
    {
        new Password("", "HASH", Password.HashType.SHA_256);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetSaltNull()
    {
        new Password(null, "HASH", Password.HashType.SHA_256);
    }

    @Test
    public void testGetSalt() throws Exception {
        assertEquals("SALT", legit.getSalt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHashEmpty()
    {
        new Password("SALT", "", Password.HashType.SHA_256);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHashNull()
    {
        new Password("SALT", null, Password.HashType.SHA_256);
    }

    @Test
    public void testGetHash() throws Exception {
        assertEquals("HASH", legit.getHash());
    }

    @Test
    public void testGetHashType() throws Exception {
        assertEquals(Password.HashType.SHA_256, legit.getHashType());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testHashTypeCannotBeNull()
    {
        new Password("SALT", "HASH", null);
    }
}