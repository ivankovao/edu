package ru.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.model.Role;
import ru.web.model.User;
import ru.web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showUsers(Model model) {
        model.addAttribute("list", userService.getUsers());
        return "/adm";
    }

    @GetMapping("/new")
    public String addUserFrm(@ModelAttribute("user") User user) {
        return "/newUser";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user) {
        Role role = new Role(null, user.getRole());
        user.getRoles().add(role);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "/updateUser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        this.userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        this.userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
