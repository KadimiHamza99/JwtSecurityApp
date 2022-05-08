package io.kadev;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.kadev.models.Role;
import io.kadev.models.User;
import io.kadev.services.UserServiceImpl;

@SpringBootApplication
public class SecureJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureJwtApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(UserServiceImpl userService) {
		return args -> {
			userService.addRole(new Role(null,"ADMIN"));
			userService.addRole(new Role(null,"USER"));
			userService.addRole(new Role(null,"MANAGER"));
			
			userService.addUser(new User(null,"KADIMI Hamza","kadev","1949",new ArrayList<Role>()));
			userService.addUser(new User(null,"LAMRANI Ali","alila","0000",new ArrayList<Role>()));
			userService.addUser(new User(null,"SALIMI Rachid","salola","1234",new ArrayList<Role>()));
			
			userService.addRoleToUser("kadev", "ADMIN");
			userService.addRoleToUser("kadev", "MANAGER");
			userService.addRoleToUser("alila", "MANAGER");
			userService.addRoleToUser("kadev", "USER");
			userService.addRoleToUser("salola", "USER");
			userService.addRoleToUser("alila", "USER");
		};
	}
	
}
