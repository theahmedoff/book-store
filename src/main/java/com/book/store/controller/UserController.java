package com.book.store.controller;

import com.book.store.model.BillingInfo;
import com.book.store.model.Role;
import com.book.store.model.User;
import com.book.store.service.UserService;
import com.book.store.util.EmailUtil;
import com.book.store.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        }
        return "redirect:/logout";
    }

}
