package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long id,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if(action.equals("delete")) {
            userService.deleteUser(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{id}")
    public String gtUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("allUsers", userService.userList(id));
        return "admin";
    }
}
