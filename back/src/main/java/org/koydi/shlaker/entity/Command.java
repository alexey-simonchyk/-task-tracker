package org.koydi.shlaker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "command")
@Data
@EqualsAndHashCode(exclude = {"developers", "projects", "company"}, callSuper = false)
@ToString(exclude = {"developers", "projects", "company"})
public class Command extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "command", fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "command", fetch = FetchType.LAZY)
    private Set<User> developers = new HashSet<>();
}
