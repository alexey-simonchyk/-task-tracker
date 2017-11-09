package org.koydi.shlaker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
@EqualsAndHashCode(exclude = {"user"}, callSuper = false)
@ToString(exclude = {"user"})
public class Comment extends BaseEntity {

    @Column(name = "text", length = 65535, columnDefinition = "text")
    private String text;

    @Column(name = "creation_time")
    private Date creationTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
