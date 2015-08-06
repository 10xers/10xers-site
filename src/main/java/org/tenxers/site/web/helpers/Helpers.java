package org.tenxers.site.web.helpers;

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
        List<User> matches = users.getByUsername(username);

        if (matches.isEmpty())
            return Optional.empty();

        if(matches.get(0).getPassword().getHash().equals(password))
            return Optional.of(matches.get(0));

        return Optional.empty();
    }

    public static void addLoginToSession(HttpSession session, User u)
    {
        session.setAttribute("loggedInUser", u);
    }

}
