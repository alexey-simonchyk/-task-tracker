package org.koydi.shlaker.controller;

import lombok.val;
import org.koydi.shlaker.dto.SignUpUserDto;
import org.koydi.shlaker.dto.UserDto;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.mapper.UserMapper;
import org.koydi.shlaker.service.UserService;
import org.koydi.shlaker.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

class PasswordsNotEqual extends BadRequestException {

    PasswordsNotEqual(String message) {
        super(message);
    }
}

@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final Validator validator;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService, Validator validator) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.validator = validator;
    }

    @GetMapping("/me")
    public UserDto getUserInformation(Authentication authentication) {
        User user = userService.getUserInformation((String)authentication.getPrincipal());
        return userMapper.toFullUser(user);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpUserDto userDto) {

        validator.Validate(userDto.getPassword(), userDto.getEmail(), userDto.getNick(), userDto.getRepeatPassword());
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new PasswordsNotEqual("Password and repeated password not equal");
        }

        User user = userMapper.signUpMapper(userDto);
        userService.createAccount(user);
    }

    @GetMapping("/user/developers")
    public List<UserDto> getDevelopers() {
        val users = userService.getDevelopers();
        return userMapper.toUserDtos(users);
    }
}
