package com.book.store.controller;

import com.book.store.model.Contact;
import com.book.store.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class ContactController {

    //fields
    @Autowired
    private EmailUtil emailUtil;
    @Value("${spring.mail.contact.to}")
    private String to;
    @Value("${spring.mail.contact.subject}")
    private String subject;

    //methods
    @PostMapping("/contact")
    public String contact(@Valid @ModelAttribute("contact")Contact contact, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message",bindingResult.getFieldError().getField() + " " +  bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/contact";
        }

        String body="Firstname: " + contact.getFirstName() + "\nLastname: " +contact.getLastName() + "\nEmail: " +contact.getEmail() + "\nSubject: " +contact.getSubject() +"\nWebsite: " +contact.getWebsite();
        emailUtil.sendEmailMessage(to, subject, body);

        return "redirect:/contact";
    }

}
