package org.tenxers.site.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.tenxers.site.core.models.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * site / Ed
 * 26/07/2015 16:52
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);

    User save(User user);

    User findById(long userId);

}
