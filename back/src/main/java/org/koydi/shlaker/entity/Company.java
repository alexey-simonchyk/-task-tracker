package org.koydi.shlaker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Company extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Command> commands = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<User> developer = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();
}
