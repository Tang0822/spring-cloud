package com.tjffy.learn.auth.repositories;

import com.tjffy.learn.auth.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jftang3
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findByUserNameLike(String userName, Pageable pageable);

    @EntityGraph(attributePaths = {"group", "group.permissions"})
    User findByUserName(String userName);
}
