package org.tenxers.site.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tenxers.site.core.PasswordMaker;
import org.tenxers.site.core.models.User;
import org.tenxers.site.core.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.tenxers.site.web.helpers.Helpers.addLoginToSession;
import static org.tenxers.site.web.helpers.Helpers.tryLogin;

/**
 * site / Ed
 * 27/07/2015 18:38
 */
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {

        Optional<User> user = tryLogin(username, password, userRepository);

        if (user.isPresent())
        {
            addLoginToSession(session, user.get());
            return "redirect:/admin/blog";
        } else {
            model.addAttribute("loginError", true);
            return "login";
        }

    }

}
