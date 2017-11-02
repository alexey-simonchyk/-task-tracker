package org.koydi.shlaker.controller;

import org.koydi.shlaker.entity.Role;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.repository.RoleRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public TestController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/hello")
    public String getHelloWorld() {
        User user = new User();
        user.setEmail("koydi");
        user.setPassword("123456");

        Role role = roleRepository.findByName("developer");
        user.setRole(role);
        userRepository.save(user);
        return user.toString();
    }
}
