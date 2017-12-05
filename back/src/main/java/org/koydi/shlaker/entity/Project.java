package org.koydi.shlaker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@EqualsAndHashCode(exclude = {"tasks", "comments", "command"}, callSuper = false)
@ToString(exclude = {"tasks", "comments", "command"})
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "FullProject",
                attributeNodes = {
                        @NamedAttributeNode(value = "comments", subgraph = "commentsWithUser"),
                        @NamedAttributeNode(value = "tasks", subgraph = "taskWithDevelopers"),
                        @NamedAttributeNode(value = "command", subgraph = "commandWithUsers")
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "taskWithDevelopers",
                                attributeNodes = {
                                        @NamedAttributeNode("developers")
                                }
                        ),
                        @NamedSubgraph(
                                name = "commentsWithUser",
                                attributeNodes = {
                                        @NamedAttributeNode("user")
                                }
                        ),
                        @NamedSubgraph(
                                name = "commandWithUsers",
                                attributeNodes = {
                                        @NamedAttributeNode("developers")
                                }
                        )
                }
        ),
        @NamedEntityGraph(
                name = "ProjectWithComments",
                attributeNodes = {
                        @NamedAttributeNode(value = "comments")
                }
        )
})

public class Project extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 65535, columnDefinition = "text")
    private String description;

    @Column(name = "start_time")
    private Date startTime = new Date();

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "status", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.DEVELOPING;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_has_comment", joinColumns = @JoinColumn(name = "project_id"),
                                                inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "command_id")
    private Command command;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
