package com.book.store.controller;

import com.book.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {


    //private BCryptPasswordEncoder encoder;

    @RequestMapping("/register")
    public String getRegisterPage(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "view/register";
    }
}
