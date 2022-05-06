package ru.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.web.model.User;
import ru.web.dao.UserDao;

@Service
@Transactional
public class UserServiceImp implements UserService {
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    public void createUser(User user) {
        this.userDao.createUser(user);
    }

    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    public User getUserById(long id) {
        return this.userDao.getUserById(id);
    }

    public void deleteUser(long id) {
        Object var3 = null;

        try {
            this.userDao.deleteUser(id);
        } catch (NullPointerException var5) {
            var5.printStackTrace();
        }

    }
}
