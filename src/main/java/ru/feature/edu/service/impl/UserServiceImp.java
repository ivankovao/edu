package ru.feature.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.feature.edu.model.User;
import ru.feature.edu.repository.UserDaoImp;
import ru.feature.edu.service.UserService;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserDaoImp userDaoImp;

    @Autowired
    public UserServiceImp(UserDaoImp userDaoImp) {
        this.userDaoImp = userDaoImp;
    }

    public UserServiceImp() {
    }

    public List<User> getAllUsers(){
        return userDaoImp.getAllUsers();
    }

    public void createUser(User var1){
        userDaoImp.createUser(var1);
    }

    public void updateUser(User var1){
        userDaoImp.updateUser(var1);
    }

    public User getUserById(long var1){
        return userDaoImp.getUserById(var1);
    }

    public void deleteUser(long var1){
        userDaoImp.deleteUser(var1);
    }
}
