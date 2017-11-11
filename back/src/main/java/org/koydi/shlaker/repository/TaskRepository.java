package org.koydi.shlaker.repository;

import org.koydi.shlaker.entity.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    @EntityGraph(value = "FullTask", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select task from Task task where task.id = :taskId")
    Task getFullTask(@Param("taskId") String taskId);
}
