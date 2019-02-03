package com.book.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

    @RequestMapping("/shop/{idCategory}")
    public String getBiographyBooks(@RequestParam("{id-category}")int idCategory, Model model){
        return "view/shop";
    }



}
