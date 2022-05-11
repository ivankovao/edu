package ru.feature.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.feature.edu.model.User;
import ru.feature.edu.repository.UserDao;
import ru.feature.edu.repository.UserDaoImp;
import ru.feature.edu.service.UserService;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImp(UserDaoImp userDaoImp) {
        this.userDao = userDaoImp;
    }

    public UserServiceImp() {
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public void createUser(User var1){
        userDao.createUser(var1);
    }

    public void updateUser(User var1){
        userDao.updateUser(var1);
    }

    public User getUserById(long var1){
        return userDao.getUserById(var1);
    }

    public void deleteUser(long var1){
        userDao.deleteUser(var1);
    }
}
