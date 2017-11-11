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
@EqualsAndHashCode(exclude = {"tasks", "comments", "developers"}, callSuper = false)
@ToString(exclude = {"tasks", "comments", "developers"})
@NamedEntityGraph(
        name = "FullProject",
        attributeNodes = {
                @NamedAttributeNode(value = "comments"),
                @NamedAttributeNode(value = "developers"),
                @NamedAttributeNode(value = "tasks")
        }
)
public class Project extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 65535, columnDefinition = "text")
    private String description;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "status", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_has_comment", joinColumns = @JoinColumn(name = "project_id"),
                                                inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_in_project", joinColumns = @JoinColumn(name = "project_id"),
                                            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> developers = new HashSet<>();
}
