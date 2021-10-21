package com.JwtProject.jwtapp;

import com.JwtProject.jwtapp.domain.AppRole;
import com.JwtProject.jwtapp.domain.AppUser;
import com.JwtProject.jwtapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(JwtAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UserService userService){
		return args -> {
			userService.saveRole(new AppRole(null,"ROLE_USER"));
			userService.saveRole(new AppRole(null,"ROLE_MANAGER"));
			userService.saveRole(new AppRole(null,"ROLE_ADMIN"));
			userService.saveRole(new AppRole(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null,"Yunus","yunus1299","12345",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Taha","taha1299","12345",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Mustafa","mustafa1299","12345",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Yahya","yahya1299","12345",new ArrayList<>()));

			userService.addRoleToUser("yunus1299","ROLE_USER");
			userService.addRoleToUser("yunus1299","ROLE_MANAGER");
			userService.addRoleToUser("taha1299","ROLE_MANAGER");
			userService.addRoleToUser("mustafa1299","ROLE_ADMIN");
			userService.addRoleToUser("yahya1299","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("yahya1299","ROLE_ADMIN");
			userService.addRoleToUser("yahya1299","ROLE_MANAGER");


		};
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
