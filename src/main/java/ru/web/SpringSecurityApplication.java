package ru.web;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.web.model.Role;
import ru.web.model.User;
import ru.web.service.UserService;

import java.util.HashSet;

@SpringBootApplication
@EnableJpaRepositories(basePackages="ru.web.repo")
@EnableTransactionManagement
@EntityScan(basePackages="ru.web.model")
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }



    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveUser(new User(null, "John Travolta", "john123", "1234", new HashSet<>()));
            userService.saveUser(new User(null, "Jim Carry", "jim777", "1234", new HashSet<>()));
            userService.saveUser(new User(null, "Will Smith", "will2022", "1234", new HashSet<>()));

          userService.addRoleToUser("john123","ROLE_USER");
            userService.addRoleToUser("jim777","ROLE_ADMIN");
            userService.addRoleToUser("jim777","ROLE_MANAGER");
            userService.addRoleToUser("will2022","ROLE_MANAGER");
            userService.addRoleToUser("will2022","ROLE_USER");
        };
    }
}
