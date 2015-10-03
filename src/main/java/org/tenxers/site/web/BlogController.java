package org.tenxers.site.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tenxers.site.core.models.Blog;
import org.tenxers.site.core.repositories.BlogRepository;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.StreamHandler;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.tenxers.site.web.helpers.Helpers.getLoggedInUser;
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
    public String blog(Model model)
    {
        List<Blog> blogs = StreamSupport.stream(blogRepository.findAll().spliterator(), false) // this is real inefficient
                .sorted((e1, e2) -> Long.compare(e2.getId(), e1.getId()))
                .collect(Collectors.toList());
        model.addAttribute("blogs", blogs);
        return "blog";
    }


}
