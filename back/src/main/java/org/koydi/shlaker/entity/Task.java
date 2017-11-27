package org.koydi.shlaker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
@EqualsAndHashCode(exclude = {"project", "comments", "developers"}, callSuper = false)
@ToString(exclude = {"project", "comments", "developers"})
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "FullTask",
                attributeNodes = {
                        @NamedAttributeNode(value = "comments", subgraph = "commentWithUser"),
                        @NamedAttributeNode(value = "developers")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "commentsWithUser",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "user")
                                }
                        )
                }
        ),
        @NamedEntityGraph(
                name = "TaskWithComments",
                attributeNodes = {
                        @NamedAttributeNode(value = "comments")
                }
        )
})

public class Task extends BaseEntity {

    @Column(name = "description", length = 65535, columnDefinition = "text")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "status", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.APPOINTED;

    @Column(name = "start_time")
    private Date startTime = new Date();

    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_has_comment", joinColumns = @JoinColumn(name = "task_id"),
                                            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_has_task", joinColumns = @JoinColumn(name = "task_id"),
                                        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> developers = new HashSet<>();
}
