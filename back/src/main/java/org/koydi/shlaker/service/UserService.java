package org.koydi.shlaker.service;

import org.koydi.shlaker.entity.Role;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.repository.RoleRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ShaPasswordEncoder shaPasswordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, ShaPasswordEncoder shaPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.shaPasswordEncoder = shaPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public void createAccount(User user) {
        user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), ""));
        Role role = roleRepository.findByName("developer");
        user.setRole(role);
        userRepository.save(user);
    }
}
