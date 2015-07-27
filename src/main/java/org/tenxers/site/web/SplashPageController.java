package org.tenxers.site.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tenxers.site.core.models.User;

import javax.servlet.http.HttpSession;

/**
 * site / Ed
 * 27/07/2015 17:57
 */
@Controller
public class SplashPageController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String splash(HttpSession session, Model model) {
        User u = (User)session.getAttribute("loggedInUser");
        if (u!=null)
        {
            model.addAttribute("loggedInPerson", u);
        }
        return "splash";
    }


}
