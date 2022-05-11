package ru.feature.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.feature.edu.service.UserService;
import ru.feature.edu.service.impl.UserServiceImp;


@Controller
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String pageIndex() {
        return "/index";
    }

    @GetMapping({"/users"})
    public String showAllUsers(Model model) {
        model.addAttribute("listUsers", this.userService.getAllUsers());
        return "/users";
    }
}
