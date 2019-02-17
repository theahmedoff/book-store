package com.book.store.controller;

import com.book.store.model.Role;
import com.book.store.model.User;
import com.book.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String getRegisterPage(@ModelAttribute("newUser")User newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.setRole(new Role(2));
        newUser.setToken(UUID.randomUUID().toString());
        newUser.setStatus(2);
        service.register(newUser);
        //send email

        return "redirect:/login";
    }
}
