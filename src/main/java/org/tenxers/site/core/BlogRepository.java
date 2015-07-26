package org.tenxers.site.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * site / Ed
 * 26/07/2015 03:35
 */
public class BlogRepository {

    private Map<Long, Blog> store = new HashMap<>();
    private Random random = new Random(System.nanoTime());

    public Optional<Blog> getById(long blogId)
    {
        if(store.containsKey(blogId))
            return Optional.of(store.get(blogId));
        else
            return Optional.empty();
    }

    public void save(Blog b) {

        if (b==null)
            throw new IllegalArgumentException("cannot save null blog");

        if (!b.getId().isPresent()) {
            b.setId(Optional.of(generateNonClashingId()));
        }

        store.put(b.getId().get(), b);
    }

    private long generateNonClashingId()
    {
        long randomId;
        do {
            randomId = random.nextLong();
        } while (randomId<=0 || store.containsKey(randomId));
        return randomId;
    }

}
