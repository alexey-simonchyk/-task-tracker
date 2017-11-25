package org.koydi.shlaker.controller;

import org.koydi.shlaker.dto.SignUpUserDto;
import org.koydi.shlaker.dto.UserDto;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.mapper.UserMapper;
import org.koydi.shlaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserDto getUserInformation(Authentication authentication) {
        User user = userService.getUserInformation((String)authentication.getPrincipal());
        return userMapper.toFullUser(user);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpUserDto userDto) {
        User user = userMapper.signUpMapper(userDto);
        userService.createAccount(user);
    }
}
