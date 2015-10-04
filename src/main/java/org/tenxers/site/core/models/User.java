package org.tenxers.site.core.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * site / Ed
 * 26/07/2015 01:40
 */
@Entity
public class User {

    private static final Pattern lettersOnly = Pattern.compile("^[a-z]+$", Pattern.CASE_INSENSITIVE);

    @Size(max=30)
    private String firstName;
    @Size(max=30)
    private String secondName;

    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="password", referencedColumnName="id",nullable=false)
    private Password password;
    @Size(max=30)
    private String username;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected User() {}

    public User(String username, Password password, String firstName, String secondName) {
        setUsername(username);
        setFirstName(firstName);
        setSecondName(secondName);
        setPassword(password);
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

    private void setUsername(String username) {

        if (username==null)
             throw new IllegalArgumentException("Username cannot be null");

        if (username.trim().isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");

        this.username = username;
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

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    private static boolean isLettersOnly(String testString)
    {
        Matcher m = lettersOnly.matcher(testString);
        return m.matches();
    }

}
