package com.book.store.controller;

import com.book.store.model.BillingInfo;
import com.book.store.model.Role;
import com.book.store.model.User;
import com.book.store.service.UserService;
import com.book.store.util.EmailUtil;
import com.book.store.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class UserController {

    //fields
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

    //methods
    @PostMapping("/register")
    public String getRegisterPage(@ModelAttribute("newUser")User newUser, RedirectAttributes redirectAttributes) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.setRole(new Role(2));
        newUser.setToken(UUID.randomUUID().toString());
        newUser.setStatus(Constants.USER_STATUS_INACTIVE);
        //billing info
        BillingInfo billingInfo = new BillingInfo(newUser);
        //register
        service.register(newUser, billingInfo);
        //send email
        emailUtil.sendEmailMessage(newUser.getEmail(), subject, String.format(body, newUser.getToken()));
        redirectAttributes.addFlashAttribute("message", "Registration successfully completed! Please check your email and activate your profile..");
        return "redirect:/login";
    }

    @RequestMapping("/activate")
    public String getActivationPage(@RequestParam(name = "token") String token, RedirectAttributes redirectAttributes) {
        service.activateUserByToken(token);
        redirectAttributes.addFlashAttribute("message", "Profile successfully activated!");
        return "redirect:/login";
    }

    @RequestMapping("/sucscribe")
    @ResponseBody
    public ResponseEntity sucscribe(@RequestParam(name = "email") String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException();
        }

        service.sucscribe(email);
        return new ResponseEntity(HttpStatus.OK);
    }

}
