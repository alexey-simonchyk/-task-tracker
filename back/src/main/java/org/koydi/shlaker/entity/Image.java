package org.koydi.shlaker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity {

    @Column(name = "path")
    private String path;

    public String getFullPath() {
        return path.replaceAll("\\{image_id}", this.getId());
    }
}
