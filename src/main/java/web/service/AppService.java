package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.Role;
import web.model.User;


import java.util.List;
import java.util.Optional;

public interface AppService extends UserDetailsService {

    List<User> findAllUsers();

    User findUser(Long userId) throws IllegalArgumentException;

    void deleteUser(Long userId);

    Iterable<Role> findAllRoles();

    void updateUser(User user);

    void insertUser(User user);

    Optional<User> findById(Long id);

}
