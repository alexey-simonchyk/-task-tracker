package org.koydi.shlaker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(exclude = {"role", "comments", "projects", "tasks"}, callSuper = false)
@ToString(exclude = {"role", "comments", "projects", "tasks"})
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "userWithRole",
                attributeNodes = {
                        @NamedAttributeNode(value = "role")
                }
        )
})
public class User extends BaseEntity {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nick")
    private String nick;

    @Column(name = "is_activated")
    private boolean isActivated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(mappedBy = "developers", fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(mappedBy = "developers", fetch = FetchType.LAZY)
    private Set<Task> tasks =  new HashSet<>();
}
