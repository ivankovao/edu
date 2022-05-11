package ru.feature.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.feature.edu.model.User;
import ru.feature.edu.service.UserService;
import ru.feature.edu.service.impl.UserServiceImp;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/new"})
    public String addUserForm(@ModelAttribute("user") User user) {
        return "/newUser";
    }

    @PostMapping({"/new"})
    public String saveUser(@ModelAttribute("user") User user) {
        this.userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping({"/{id}/edit"})
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "/updateUser";
    }

    @PostMapping({"/update"})
    public String updateUser(@ModelAttribute("user") User user) {
        this.userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping({"/delete"})
    public String deleteUser(@RequestParam("id") long id) {
        this.userService.deleteUser(id);
        return "redirect:/users";
    }
}
