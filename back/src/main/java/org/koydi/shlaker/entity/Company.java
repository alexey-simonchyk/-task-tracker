package org.koydi.shlaker.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
@Data
@EqualsAndHashCode(exclude = {"commands", "projects", "developers"}, callSuper = false)
@ToString(exclude = {"commands", "projects", "developers"})
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "withDevelopers",
                attributeNodes = {
                        @NamedAttributeNode("developers")
                }
        )
})
public class Company extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Command> commands = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<User> developers = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();
}
