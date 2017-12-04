package org.koydi.shlaker;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.koydi.shlaker.entity.User;
import org.koydi.shlaker.service.ProjectService;
import org.koydi.shlaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShlakerApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

	@Test
	public void findAllDevelopers() {

	}

}
