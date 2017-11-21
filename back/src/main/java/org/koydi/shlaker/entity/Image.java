package org.koydi.shlaker.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Data
@EqualsAndHashCode(callSuper = false)
public class Image extends BaseEntity {

    @Column(name = "path")
    private String path;
}
