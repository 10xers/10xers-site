package org.tenxers.site.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
