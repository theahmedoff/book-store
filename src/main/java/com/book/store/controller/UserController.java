package com.book.store.controller;

import com.book.store.model.Role;
import com.book.store.model.User;
import com.book.store.service.UserService;
import com.book.store.util.EmailUtil;
import com.book.store.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private UserService service;

    @Value("${spring.mail.register.subject}")
    private String subject;
    @Value("${spring.mail.register.body}")
    private String body;

    @PostMapping("/register")
    public String getRegisterPage(@ModelAttribute("newUser")User newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.setRole(new Role(2));
        newUser.setToken(UUID.randomUUID().toString());
        newUser.setStatus(Constants.USER_STATUS_INACTIVE);
        service.register(newUser);
        //send email
        emailUtil.sendEmailMessage(newUser.getEmail(), subject, String.format(body, newUser.getToken()));
        return "redirect:/login";
    }

    @RequestMapping("/activate")
    public String getActivationPage(@RequestParam(name = "token") String token, RedirectAttributes redirectAttributes) {
        service.activateUserByToken(token);
        redirectAttributes.addFlashAttribute("message", "Profile successfully activated!");
        return "redirect:/login";
    }

}
