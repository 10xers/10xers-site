package org.tenxers.site.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static org.tenxers.site.web.helpers.Helpers.isLoggedIn;

/**
 * site / Ed
 * 27/07/2015 18:38
 */
@Controller
public class BlogController {

    @RequestMapping(value="/blog", method = RequestMethod.GET)
    public String blog()
    {
        return "blog";
    }

    @RequestMapping(value="/blog/admin", method = RequestMethod.GET)
    public String blogAdmin(HttpSession session) {
        if (isLoggedIn(session))
        {
            return "blog";
        } else {
            return "login";
        }
    }

}
