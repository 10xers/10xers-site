package org.tenxers.site.core.models;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * site / Ed
 * 26/07/2015 01:40
 */
public class User {

    private static final Pattern lettersOnly = Pattern.compile("^[a-z]+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern numbersAndLettersOnly = Pattern.compile("^[a-z0-9]+$", Pattern.CASE_INSENSITIVE);

    private String firstName;
    private String secondName;
    private Password password;

    private Optional<Long> id;

    public User(Optional<Long> id, String firstName, String secondName, Password password) {
        setFirstName(firstName);
        setSecondName(secondName);
        setPassword(password);
        setId(id);
    }

    public final void setId(Optional<Long> id)
    {
        if (id == null)
            throw new IllegalArgumentException("id is optional but cannot be null");

        if (id.isPresent() && id.get() <= 0)
           throw new IllegalArgumentException("ID must be greater than 0");

        this.id = id;
    }

    private final void setPassword(Password password)
    {
        if (password == null)
            throw new IllegalArgumentException("password cannot be null!");

        this.password = password;
    }

    private final void setSecondName(String secondName)
    {
        if (secondName == null)
            throw new IllegalArgumentException("Second name cannot be null");

        if (!isLettersOnly(secondName))
            throw new IllegalArgumentException("Second name must only be letters [a-zA-Z]");

        this.secondName = secondName;
    }

    private final void setFirstName(String firstName)
    {
        if (firstName==null)
            throw new IllegalArgumentException("First name cannot be null");

        if (!isLettersOnly(firstName))
            throw new IllegalArgumentException("First name must only be letters [a-zA-Z]");

        this.firstName = firstName;
    }

    public boolean hasUserId()
    {
        return this.getId().isPresent();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Password getPassword() {
        return this.password;
    }

    public Optional<Long> getId() {
        return this.id;
    }

    private static boolean isLettersOnly(String testString)
    {
        Matcher m = lettersOnly.matcher(testString);
        return m.matches();
    }

}
