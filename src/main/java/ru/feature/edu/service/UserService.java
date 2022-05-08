package ru.feature.edu.service;

import ru.feature.edu.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(User var1);

    void updateUser(User var1);

    User getUserById(long var1);

    void deleteUser(long var1);
}
