package org.koydi.shlaker.repository;

import org.koydi.shlaker.entity.Project;
import org.koydi.shlaker.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    @EntityGraph(value = "FullProject", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select project from Project project where project.id = :projectId")
    Project getFullProject(@Param("projectId") String projectId);

    @EntityGraph(value = "ProjectWithComments", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select project from Project project where project.id = :projectId")
    Project getProjectWithComments(@Param("projectId") String projectId);

}
