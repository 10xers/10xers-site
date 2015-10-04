package org.tenxers.site.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tenxers.site.core.models.User;
import org.tenxers.site.core.models.Blog;
import org.tenxers.site.core.repositories.BlogRepository;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.StreamHandler;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * site / Ed
 * 27/07/2015 17:57
 */
@Controller
public class SplashPageController {

    @Autowired
    BlogRepository blogRepository;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String splash(HttpSession session, Model model) {

        User u = (User)session.getAttribute("loggedInUser");
	
        if (u!=null) {
            model.addAttribute("loggedInPerson", u);
        }

	    List<Blog> blogs = StreamSupport.stream(blogRepository.findAll().spliterator(), false)
                .sorted((e1, e2) -> Long.compare(e2.getId(), e1.getId()))
                .collect(Collectors.toList());
	
        model.addAttribute("posts", blogs);
	
        return "splash";
    }


}
