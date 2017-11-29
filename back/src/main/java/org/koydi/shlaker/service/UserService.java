package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.RoleRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class SuchUserExists extends BadRequestException {

    SuchUserExists(String message) {
        super(message);
    }
}

class UserNotFound extends BadRequestException {

    UserNotFound(String message) {
        super(message);
    }
}


@Service
public class UserService {

    private final ShaPasswordEncoder shaPasswordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       ShaPasswordEncoder shaPasswordEncoder,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.shaPasswordEncoder = shaPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public void createAccount(User user) {
        Optional.ofNullable(userRepository.findByEmail(user.getEmail())).ifPresent(s -> {
            throw new SuchUserExists("Such user exists");
        });

        user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), ""));
        val role = roleRepository.findByName("developer");
        user.setRole(role);
        userRepository.save(user);
    }

    public User getUserInformation(String userId) {
        return Optional.ofNullable(userRepository.getFullUser(userId))
                .orElseThrow(() -> new UserNotFound("User with id not found"));
    }

    public Set<User> getDevelopers() {
        return Optional
                .ofNullable(userRepository.getUsersByRoleName("developer"))
                .orElse(new HashSet<>());
    }
}
