package org.koydi.shlaker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@EqualsAndHashCode(exclude = {"users"}, callSuper = false)
@ToString(exclude = {"users"})
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
}
