package com.book.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

    @RequestMapping("/shop")
    public String getBiographyBooks(@RequestParam("{id-category}")int idCategory){
        return "view/shop";
    }



}
