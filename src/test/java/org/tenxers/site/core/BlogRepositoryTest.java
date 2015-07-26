package org.tenxers.site.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * site / Ed
 * 26/07/2015 03:37
 */
public class BlogRepositoryTest {

    BlogRepository repository;
    Blog emptyId;
    Blog legitId;
    User author;

    @Before
    public void setUp() throws Exception {
        repository = new BlogRepository();
        author = new User(Optional.of(99L), "Ed", "Lewis", "ABC123");
        emptyId = new Blog(Optional.empty(), "Lorem Ipsum", "Lorem ipsum lorem ipsum", author);
        legitId = new Blog(Optional.of(123L), "Lorem Ipsum #2", "Ipsuim lorem lorem", author);
        repository.save(legitId);
    }

    @Test
    public void testGetById() throws Exception {
        Optional<Blog> result = repository.getById(legitId.getId().get());
        assertTrue("result not found", result.isPresent());
        Blog res = result.get();
        assertEquals(legitId.getId().get(), res.getId().get()); // woah.
        assertEquals(Optional.empty(), repository.getById(234243L));
    }

    @Test (timeout=100)
    public void testSave() throws Exception {
        assertFalse("no id before save", emptyId.getId().isPresent());
        repository.save(emptyId);
        assertTrue("id after save", emptyId.getId().isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotSaveNull()
    {
        repository.save(null);
    }
}