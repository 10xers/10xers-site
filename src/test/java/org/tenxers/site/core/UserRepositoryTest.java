package org.tenxers.site.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 16:52
 */
public class UserRepositoryTest {

    private UserRepository repository;
    private User legitId;
    private User newUser;

    @Before
    public void setUp() throws Exception {
        repository = new UserRepository();
        legitId = new User(Optional.of(10L), "Ed", "Lewis", PasswordMaker.make("ABC123"));
        newUser = new User(Optional.empty(), "Timmy", "Turtle", PasswordMaker.make("POTATO"));
        repository.save(legitId);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCannotSaveNull()
    {
        repository.save(null);
    }

    @Test
    public void testGetById()
    {
        Optional<User> u = repository.getById(legitId.getId().get());
        assertTrue(u.isPresent());
        assertEquals(u.get().getId().get(), legitId.getId().get());
        assertEquals(Optional.empty(), repository.getById(3242));
    }

    @Test (timeout = 100)
    public void testSave() {
        assertFalse(newUser.hasUserId());
        repository.save(newUser);
        assertTrue(newUser.hasUserId());
    }
}