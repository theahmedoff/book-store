package com.book.store.controller;

import com.book.store.model.User;
import com.book.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class AuthenticatedActionsController {

    //fields
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserService service;

    //methods
    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) service.loadUserByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        return "view/profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute(name = "user") User user,
                                @RequestParam(name = "password") String password,
                                @RequestParam(name = "confirmPassword") String confirmPassword) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (password.equals(confirmPassword)) {
            user.setEmail(currentUser.getEmail());
            user.setPassword(encoder.encode(password));
            service.updateUser(user);

            currentUser.setUsername(user.getUsername());
        }
        return "redirect:/user/profile";
    }

}
