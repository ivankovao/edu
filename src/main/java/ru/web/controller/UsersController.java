package ru.web.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.web.model.User;
import ru.web.service.UserService;

@Controller
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({""})
    public String pageIndex() {
        return "/index";
    }

    @GetMapping({"/users"})
    public String showAllUsers(Model model) {
        model.addAttribute("listUsers", this.userService.getAllUsers());
        return "/users";
    }

    @GetMapping({"/new"})
    public String addUserForm(@ModelAttribute("user") User user) {
        return "/newUser";
    }

    @PostMapping({"/new"})
    public String saveUser(@ModelAttribute("user") @Valid User user) {
        this.userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping({"/users/{id}/edit"})
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "/updateUser";
    }

    @PostMapping({"/update"})
    public String updateUser(@ModelAttribute("user") @Valid User user) {
        this.userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping({"/users/delete"})
    public String deleteUser(@RequestParam("id") long id) {
        this.userService.deleteUser(id);
        return "redirect:/users";
    }
}
