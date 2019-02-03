package com.book.store.controller;

import com.book.store.model.Category;
import com.book.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/shop/{idCategory}")
    public String getBiographyBooks(@RequestParam("{id-category}")int idCategory, Model model){
        return "view/shop";
    }


    @RequestMapping("/test")
    @ResponseBody
    public List<Category> getAllCategories( ){
        return bookService.getAllCategories();
    }




}
