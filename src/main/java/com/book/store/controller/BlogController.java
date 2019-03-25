package com.book.store.controller;

import com.book.store.model.Blog;
import com.book.store.model.BlogReview;
import com.book.store.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blog-details/{idBlog}")
    public String getBlogById(@PathVariable("idBlog") int idBlog, Model model) {
        Blog blog = blogService.getBlogById(idBlog);
        model.addAttribute("blog", blog);
        return "view/blog-details";
    }

}
