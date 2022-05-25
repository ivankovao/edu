package ru.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.app.model.User;
import ru.web.app.repo.UserRepository;
import ru.web.app.service.AppService;

import javax.validation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AppService appService;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(AppService appService, UserRepository userRepository) {
        this.appService = appService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", appService.findAllUsers());
        model.addAttribute("allRoles", appService.findAllRoles());

        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "admin-page";
    }

    @GetMapping("/{id}/profile")
    public String showUserProfileModal(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("allRoles", appService.findAllRoles());
        model.addAttribute("user", appService.findUser(userId));
        return "fragments/user-form";
    }

    @PatchMapping()
    public String updateUser(@Valid @ModelAttribute("user") User user) {
        appService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping()
    public String deleteUser(@ModelAttribute("user") User user) {
        appService.deleteUser(user.getId());
        return "redirect:/admin";
    }

    @PostMapping()
    public String insertUser(@ModelAttribute("user") User user) {
        appService.insertUser(user);
        return "redirect:/admin";
    }
}
