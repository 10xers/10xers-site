package org.tenxers.site.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tenxers.site.core.models.Blog;
import org.tenxers.site.core.repositories.BlogRepository;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.tenxers.site.web.helpers.Helpers.isLoggedIn;

/**
 * site / Ed
 * 27/07/2015 18:38
 */
@Controller
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping(value="/blog", method = RequestMethod.GET)
    public String blog()
    {
        return "blog";
    }

    @RequestMapping(value="/blog/admin", method = RequestMethod.GET)
    public String blogAdmin(HttpSession session, Model model) {
        if (isLoggedIn(session))
        {
            List<Blog> blogs = StreamSupport.stream(blogRepository.findAll().spliterator(), false).collect(Collectors.toList());
            model.addAttribute("blogs", blogs);
            return "adminblog";
        } else {
            return "login";
        }
    }

    @RequestMapping(value="/blog/new", method = RequestMethod.GET)
    public String blogNew(HttpSession session)
    {
        if (isLoggedIn(session))
        {
            return "newblog";
        } else {
            return "redirect:/login";
        }
    }
    
}
