package com.tjffy.learn.authuser.repositories;

import com.tjffy.learn.authuser.entity.Permission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jftang3
 */
@Repository
public interface AuthRepository extends JpaRepository<Permission, Integer> {

    @Override
    @EntityGraph(attributePaths = {"groups"})
    List<Permission> findAll();
}
