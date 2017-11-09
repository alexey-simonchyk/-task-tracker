package org.koydi.shlaker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String id;
    private String text;
    private Date creationDate;

    private UserDto user;
}
