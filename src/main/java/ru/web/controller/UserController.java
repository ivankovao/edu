package ru.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.web.service.UserService;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String pageIndex() {
        return "/index";
    }

    @GetMapping("/user")
    public String showAllUsers(Model model) {
        model.addAttribute("listUsers", userService.getUsers());
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }
}
