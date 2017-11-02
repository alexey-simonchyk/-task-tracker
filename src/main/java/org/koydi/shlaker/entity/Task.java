package org.koydi.shlaker.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
public class Task extends BaseEntity {

    @Column(name = "description", length = 65535, columnDefinition = "text")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "status", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToMany()
    @JoinTable(name = "task_has_comment", joinColumns = @JoinColumn(name = "task_id"),
                                            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "user_has_task", joinColumns = @JoinColumn(name = "task_id"),
                                        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> developers = new HashSet<>();
}
