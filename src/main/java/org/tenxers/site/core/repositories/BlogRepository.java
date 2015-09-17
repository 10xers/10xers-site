package org.tenxers.site.core.repositories;

import org.tenxers.site.core.models.Blog;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

/**
 * site / Ed
 * 26/07/2015 03:35
 */
public interface BlogRepository extends CrudRepository<Blog, Long> {


    List<Blog> findById(long blogId);
    Blog save(Blog b);

}
