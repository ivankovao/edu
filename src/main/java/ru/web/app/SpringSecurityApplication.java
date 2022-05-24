package ru.web.app;

import com.sun.xml.bind.DatatypeConverterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.web.app.model.Role;
import ru.web.app.model.User;
import ru.web.app.repo.RoleRepository;
import ru.web.app.repo.UserRepository;
import ru.web.app.service.AppService;

import java.util.HashSet;

@SpringBootApplication
@EnableJpaRepositories(basePackages="ru.web.app.repo")
@EnableTransactionManagement
@EntityScan(basePackages="ru.web.app.model")
public class SpringSecurityApplication {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppService appService;

    @Autowired
    public SpringSecurityApplication(RoleRepository roleRepository,
                                     UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     AppService appService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.appService = appService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }



    @Bean
    CommandLineRunner run(RoleRepository roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          AppService appService) {
        return args -> {
            Role user = new Role(null,"ROLE_USER");
            Role admin = new Role(null,"ROLE_ADMIN");
            roleRepository.save(admin);
            roleRepository.save(user);
            userRepository.save(new User(null,"John", "Travolta",   "john@mail.ru",
                    passwordEncoder.encode("1234"),55, new HashSet<>() {{add(user);}}));
            userRepository.save(new User(null, "Jim", "Carry",  "jim@gmail.com",
                    passwordEncoder.encode("1234"),41, new HashSet<>() {{add(admin); add(user); }}));
            userRepository.save(new User(null, "Will", "Smith",  "will@list.ru",
                    passwordEncoder.encode("1234"),25, new HashSet<>() {{add(user);}}));

        };
    }


}
