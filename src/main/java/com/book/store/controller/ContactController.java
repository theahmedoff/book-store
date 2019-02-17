package com.book.store.controller;

import com.book.store.model.Contact;
import com.book.store.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private EmailUtil emailUtil;

    @Value("${spring.mail.contact.to}")
    private String to;

    @Value("${spring.mail.contact.subject}")
    private String subject;


    @PostMapping("/contact")
    public String contact(@Valid @ModelAttribute("contact")Contact contact, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message",bindingResult.getFieldError().getField() + " " +  bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/contact";

        }

<<<<<<< HEAD

//        emailUtil.sendEmailMessage(contact);
=======
        String body="Firstname: " + contact.getFirstName() + "\nLastname: " +contact.getLastName() + "\nEmail: " +contact.getEmail() + "\nSubject: " +contact.getSubject() +"\nWebsite: " +contact.getWebsite();
        emailUtil.sendEmailMessage(to, subject, body);
>>>>>>> 80090f0eb0844bfaaa5545c1a1551b4e13163ac1

        return "redirect:/contact";

    }

}
