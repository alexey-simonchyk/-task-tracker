package org.koydi.shlaker.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User extends BaseEntity {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(mappedBy = "developers", fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(mappedBy = "developers", fetch = FetchType.LAZY)
    private Set<Task> tasks =  new HashSet<>();
}
