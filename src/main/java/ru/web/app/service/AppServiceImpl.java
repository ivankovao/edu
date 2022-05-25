package ru.web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.web.app.model.Role;
import ru.web.app.model.User;
import ru.web.app.repo.RoleRepository;
import ru.web.app.repo.UserRepository;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AppServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", email))
        );
    }

    @Override
    public Iterable<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id", "firstName", "lastName"));
    }

    @Override
    public User findUser(Long userId) throws IllegalArgumentException {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException(String.format("User with ID %d not found", userId)));
    }

    @Override
    public void insertUser(User user) {
        String oldPassword = user.getPassword();
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            user.setPassword(oldPassword);
        }
    }

    @Override
    public void updateUser(User user) {
            String oldPassword = user.getPassword();
            try {
                user.setPassword(user.getPassword().isEmpty() ?
                        findUser(user.getId()).getPassword() :
                        passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            } catch (DataIntegrityViolationException e) {
                user.setPassword(oldPassword);
            }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
