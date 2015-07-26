package org.tenxers.site.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * site / Ed
 * 26/07/2015 16:52
 */
public class UserRepository {

    private Map<Long, User> store = new HashMap<>();
    private Random random = new Random(System.nanoTime());

    public void save(User user) {
        if (user == null)
            throw new IllegalArgumentException("Cannot save null");

        if (!user.hasUserId())
            user.setId(Optional.of(generateUniqueUserId()));

        store.put(user.getId().get(), user);
    }

    public Optional<User> getById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    private long generateUniqueUserId() {
        long newId;
        do {
            newId = random.nextLong();
        } while (newId <= 0 || store.containsKey(newId));

        return newId;
    }
}
