package org.tenxers.site;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.tenxers.site.core.PasswordMaker;
import org.tenxers.site.core.models.User;
import org.tenxers.site.core.repositories.UserRepository;

/**
 * site / Ed
 * 26/07/2015 01:28
 */
@SpringBootApplication
public class Application {

    public static final void main(String args[])
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner seed(UserRepository repository) {
        return (args) -> {
            if (repository.findByUsername("admin").isEmpty()) {
                User admin = new User("admin", PasswordMaker.make("wTT6pvKD9S"), "Ed", "Lewis"); // TODO development only!
                repository.save(admin);
            }
        };
    }

}
