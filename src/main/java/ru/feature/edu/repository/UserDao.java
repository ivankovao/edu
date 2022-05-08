package ru.feature.edu.repository;


import ru.feature.edu.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void createUser(User var1);

    void updateUser(User var1);

    User getUserById(long var1);

    void deleteUser(long var1);
}
