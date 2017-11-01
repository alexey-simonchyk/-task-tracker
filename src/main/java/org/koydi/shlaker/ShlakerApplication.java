package org.koydi.shlaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.koydi.shlaker.*")
public class ShlakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShlakerApplication.class, args);
	}
}
