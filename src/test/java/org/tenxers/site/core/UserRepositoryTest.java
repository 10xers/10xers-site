package org.tenxers.site.core;

import org.junit.Before;
import org.junit.Test;
import org.tenxers.site.core.models.User;
import org.tenxers.site.core.repositories.UserRepository;

import java.util.LinkedList;
import java.util.List;
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
        legitId = new User(Optional.of(10L), "edlewis", PasswordMaker.make("ABC123"), "Ed", "Lewis");
        newUser = new User(Optional.empty(), "timmyturtle", PasswordMaker.make("POTATO"), "Timmy", "Turtle");
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

    @Test
    public void testGetByUsername() throws Exception {
        assertEquals(0, repository.getByUsername("tasedfasdfas").size());
        List<User> users = repository.getByUsername("edlewis");
        assertEquals(1, users.size());
        assertEquals(10L, (long)users.get(0).getId().get());

        assertNotNull(repository.getByUsername(null));
    }
}