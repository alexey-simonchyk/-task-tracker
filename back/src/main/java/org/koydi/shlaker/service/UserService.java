package org.koydi.shlaker.service;

import org.koydi.shlaker.entity.Company;
import org.koydi.shlaker.entity.Role;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.CompanyRepository;
import org.koydi.shlaker.repository.RoleRepository;
import org.koydi.shlaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

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

class RoleNotExists extends BadRequestException {

    RoleNotExists(String message) {
        super(message);
    }
}

class NotAllDataForRegistartion extends BadRequestException {

    public NotAllDataForRegistartion(String message) {
        super(message);
    }
}


@Service
@Transactional
public class UserService {

    final static Function<String, String> userNotFoundErrorMessage =
            userId -> String.format("User with id %s not found", userId);

    private final ShaPasswordEncoder shaPasswordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       ShaPasswordEncoder shaPasswordEncoder,
                       RoleRepository roleRepository,
                       CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.shaPasswordEncoder = shaPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public void createAccount(User user, String companyName, String companyId) {
        if ((companyName == null || companyName.isEmpty()) &&
                (companyId == null || companyId.isEmpty())) {
            throw new NotAllDataForRegistartion("Company not specified");
        }


        Optional.ofNullable(userRepository.findByEmail(user.getEmail())).ifPresent(s -> {
            throw new SuchUserExists("Such user exists");
        });

        user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), ""));
        Role role;
        Company company;
        if (companyName == null || companyName.isEmpty()) {
             role = Optional.ofNullable(roleRepository.findByName("developer"))
                    .orElseThrow(() -> new RoleNotExists("No such role"));
             company = Optional.ofNullable(companyRepository.getCompanyWithDevelopers(companyId))
                                .orElseThrow(() -> new CompanyNotFound("No such company"));
             user.setActivated(true);

        } else {
            company = new Company();
            company.setName(companyName);
            role = Optional.ofNullable(roleRepository.findByName("director"))
                    .orElseThrow(() -> new RoleNotExists("No such role"));
            user.setActivated(false);
            company = companyRepository.save(company);
        }

        user.setCompany(company);
        user.setRole(role);
        userRepository.save(user);
    }

    public User getUserInformation(String userId) {
        return Optional.ofNullable(userRepository.getFullUser(userId))
                .orElseThrow(() -> new UserNotFound(userNotFoundErrorMessage.apply(userId)));
    }

    public User getUserWithCompany(String userId) {
        return Optional.ofNullable(userRepository.getUserWithCompany(userId))
                .orElseThrow(() -> new UserNotFound(userNotFoundErrorMessage.apply(userId)));
    }

    public User getUserWithRole(String userId) {
        return Optional.ofNullable(userRepository.getUserWithRole(userId))
                .orElseThrow(() -> new UserNotFound(userNotFoundErrorMessage.apply(userId)));
    }

    public Set<User> getDevelopers() {
        return Optional
                .ofNullable(userRepository.getAllUsers())
                .orElse(new HashSet<>());
    }
}
