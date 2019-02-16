package com.book.store.controller;

import com.book.store.model.Contact;
import com.book.store.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/contact")
    public String contact(@Valid @ModelAttribute("contact")Contact contact, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message",bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/contact";


        }


        emailUtil.sendEmailMessage(contact);

        return "redirect:/contact";

    }

}
