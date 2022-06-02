package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;
import web.service.AppService;


import java.util.HashSet;


@SpringBootApplication
@EnableJpaRepositories(basePackages="web.repository")
@EnableTransactionManagement
@EntityScan(basePackages="web.model")
public class Application {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppService appService;

    @Autowired
    public Application(RoleRepository roleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AppService appService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.appService = appService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



    @Bean
    CommandLineRunner run(RoleRepository roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AppService appService) {
        return args -> {
            Role user = new Role("ROLE_USER");
            Role admin = new Role("ROLE_ADMIN");
            roleRepository.save(admin);
            roleRepository.save(user);
            userRepository.save(new User("John", "Travolta",   55,"john@mail.ru",
                    passwordEncoder.encode("1234"), new HashSet<Role>() {{add(user);}}));
            userRepository.save(new User("Jim", "Carry",  41, "jim@gmail.com",
                    passwordEncoder.encode("1234"), new HashSet<Role>() {{add(user);add(admin);}}));
            userRepository.save(new User("Will", "Smith", 25, "will@list.ru",
                    passwordEncoder.encode("1234"), new HashSet<Role>() {{add(user);}}));
        };
    }
}
