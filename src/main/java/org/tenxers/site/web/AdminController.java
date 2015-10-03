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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.tenxers.site.web.helpers.Helpers.getLoggedInUser;
import static org.tenxers.site.web.helpers.Helpers.isLoggedIn;

/**
 * site / Ed
 * 03/10/2015 18:12
 */
@Controller
public class AdminController {

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping(value = "/admin/blog", method = RequestMethod.GET)
    public String blogAdmin(HttpSession session, Model model) {
        if (isLoggedIn(session)) {
            List<Blog> blogs = StreamSupport.stream(blogRepository.findAll().spliterator(), false) // and here
                    .sorted((e1, e2) -> Long.compare(e2.getId(), e1.getId()))
                    .collect(Collectors.toList());
            model.addAttribute("blogs", blogs);
            return "adminblog";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/admin/blog/new", method = RequestMethod.GET)
    public String blogNew(HttpSession session) {
        if (isLoggedIn(session)) {
            return "newblog";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/admin/blog/new", method = RequestMethod.POST)
    public String blogNewPost(@RequestParam String title, @RequestParam String content, @RequestParam Long id, HttpSession session, Model model) {
        if (isLoggedIn(session)) {
            Blog toEdit;
            if (id==null) {
                toEdit = new Blog(title, content, getLoggedInUser(session).get());
            } else {
                toEdit = blogRepository.findById(id).get(0);
                toEdit.setText(content);
                toEdit.setTitle(title);
            }
            blogRepository.save(toEdit);
            return "redirect:/admin/blog";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/admin/blog/delete/")
    public String blogDeletePost(@RequestParam Long id, HttpSession session) {
        if (isLoggedIn(session)) {
            blogRepository.delete(id);
            return "redirect:/admin/blog"; // erh should do something with this
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/admin/blog/edit/")
    public String blogEditPost(@RequestParam Long id, HttpSession session, Model model) {
        if (isLoggedIn(session))
        {
            model.addAttribute("blog", blogRepository.findById(id).get(0));
            return "newblog";
        } else {
            return "redirect:/login";
        }
    }

}
