package org.koydi.shlaker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpUserDto {
    private String nick;
    private String email;
    private String password;
    private String repeatPassword;
}
