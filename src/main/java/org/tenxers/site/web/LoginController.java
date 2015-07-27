package org.tenxers.site.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tenxers.site.core.PasswordMaker;
import org.tenxers.site.core.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * site / Ed
 * 27/07/2015 18:38
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {

        if ("user".equals(username) && "password".equals(password))
        {
            User xyz = new User(Optional.of(123L), "Ed", "Lewis", PasswordMaker.make("password"));
            session.setAttribute("loggedInUser", xyz);
            return "redirect:/";
        } else {
            model.addAttribute("loginError", true);
            return "login";
        }

    }

}
