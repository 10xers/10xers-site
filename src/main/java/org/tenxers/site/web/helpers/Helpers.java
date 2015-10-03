package org.tenxers.site.web.helpers;

import org.tenxers.site.core.PasswordMaker;
import org.tenxers.site.core.models.Password;
import org.tenxers.site.core.models.User;
import org.tenxers.site.core.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * site / Ed
 * 06/08/2015 18:33
 */
public class Helpers {

    public static boolean isPasswordMatch(Password password, String testString)
    {
        if (password == null)
            throw new IllegalArgumentException("Password cannot be null");

        if (testString==null)
            return false; // non null != null

        Password test = PasswordMaker.make(testString, password.getSalt());

        return password.equals(test);
    }


    public static boolean isLoggedIn(HttpSession httpSession)
    {
        if (httpSession==null)
            throw new IllegalArgumentException("httpSession cannot be null");

        User u;

        try {
            u = (User) httpSession.getAttribute("loggedInUser");
        } catch (ClassCastException e)
        {
            throw new IllegalStateException(e);
        }

        if (u==null)
            return false;

        return true;
    }

    public static Optional<User> tryLogin(String username, String password, UserRepository users)
    {
        List<User> matches = users.findByUsername(username);

        if (matches.isEmpty())
            return Optional.empty();

        if(isPasswordMatch(matches.get(0).getPassword(), password))
            return Optional.of(matches.get(0));

        return Optional.empty();
    }

    public static void addLoginToSession(HttpSession session, User u)
    {
        session.setAttribute("loggedInUser", u);
    }

    public static Optional<User> getLoggedInUser(HttpSession session) {
        return Optional.ofNullable((User)session.getAttribute("loggedInUser"));
    }

}
