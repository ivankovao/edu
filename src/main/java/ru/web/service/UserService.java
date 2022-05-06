package ru.web.service;

import java.util.List;
import ru.web.model.User;

public interface UserService {
    List<User> getAllUsers();

    void createUser(User var1);

    void updateUser(User var1);

    User getUserById(long var1);

    void deleteUser(long var1);
}
