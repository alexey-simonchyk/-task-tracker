package org.koydi.shlaker.repository;

import org.koydi.shlaker.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(value = "userWithRole", type = EntityGraph.EntityGraphType.LOAD)
    User findByEmail(String email);

    @EntityGraph(value = "userWithRole", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select u from User u where u.id = :userId")
    User getFullUser(@Param("userId") String userId);
}
