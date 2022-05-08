package ru.feature.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.feature.edu.model.User;
import ru.feature.edu.service.impl.UserServiceImp;


@Controller
public class UsersController {

    private UserServiceImp userServiceImp;

    @Autowired
    public UsersController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/")
    public String pageIndex() {
        return "/index";
    }

    @GetMapping({"/users"})
    public String showAllUsers(Model model) {
        model.addAttribute("listUsers", this. userServiceImp.getAllUsers());
        return "/users";
    }

    @GetMapping({"/new"})
    public String addUserForm(@ModelAttribute("user") User user) {
        return "/newUser";
    }

    @PostMapping({"/new"})
    public String saveUser(@ModelAttribute("user") User user) {
        this. userServiceImp.createUser(user);
        return "redirect:/users";
    }

    @GetMapping({"/users/{id}/edit"})
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", this. userServiceImp.getUserById(id));
        return "/updateUser";
    }

    @PostMapping({"/update"})
    public String updateUser(@ModelAttribute("user") User user) {
        this.userServiceImp.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping({"/users/delete"})
    public String deleteUser(@RequestParam("id") long id) {
        this.userServiceImp.deleteUser(id);
        return "redirect:/users";
    }
}
