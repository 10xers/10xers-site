package org.tenxers.site.core;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tenxers.site.Application;
import org.tenxers.site.core.models.User;
import org.tenxers.site.core.repositories.UserRepository;

import java.util.List;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 16:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Ignore
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    private User legitId;
    private User newUser;

    @Before
    public void setUp() throws Exception {
        legitId = new User("edlewis", PasswordMaker.make("ABC123"), "Ed", "Lewis");
        newUser = new User("timmyturtle", PasswordMaker.make("POTATO"), "Timmy", "Turtle");
        repository.save(legitId);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCannotSaveNull()
    {
        repository.save((User)null);
    }

    @Test
    public void testGetById()
    {
        User u = repository.findById(legitId.getId());
        assertNotNull(u);
        assertEquals(u.getId(), legitId.getId());
        assertNull(repository.findById(3242));
    }

    @Test (timeout = 100)
    public void testSave() {
        repository.save(newUser);
    }

    @Test
    public void testGetByUsername() throws Exception {
        assertEquals(0, repository.findByUsername("tasedfasdfas").size());
        List<User> users = repository.findByUsername("edlewis");
        assertEquals(1, users.size());
        assertEquals(10L, (long)users.get(0).getId());

        assertNotNull(repository.findByUsername(null));
    }
}