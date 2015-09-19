package org.tenxers.site.core;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tenxers.site.Application;
import org.tenxers.site.core.models.Blog;
import org.tenxers.site.core.models.Password;
import org.tenxers.site.core.models.User;
import org.tenxers.site.core.repositories.BlogRepository;
import testcats.SlowTests;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * site / Ed
 * 26/07/2015 03:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Category(SlowTests.class)
public class BlogRepositoryTest {

    @Autowired
    BlogRepository repository;
    Blog emptyId;
    Blog legitId;
    User author;
    Password samplePassword;

    @Before
    public void setUp() throws Exception {
        samplePassword = PasswordMaker.make("ABC123");
        author = new User("edlewis", samplePassword, "Ed", "Lewis");


        emptyId = new Blog("Lorem Ipsum", "Lorem ipsum lorem ipsum", author);
        legitId = new Blog("Lorem Ipsum #2", "Ipsuim lorem lorem", author);
        repository.save(legitId);
    }
/*
    @Test
    public void testGetById() throws Exception {
        List<Blog> result = repository.findById(legitId.getId());
        assertTrue("result not found", result.size() == 1);
        Blog res = result.get(0);
        assertEquals(legitId.getId(), res.getId());
    }

    @Test (timeout=100)
    public void testSave() throws Exception {
        repository.save(emptyId);
    }
*/
    @Test(expected = IllegalArgumentException.class)
    public void testCannotSaveNull()
    {
        repository.save((Blog)null);
    }
}