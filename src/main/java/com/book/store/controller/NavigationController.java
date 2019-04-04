package com.book.store.controller;

import com.book.store.model.*;
import com.book.store.service.BlogService;
import com.book.store.service.BookService;
import com.book.store.util.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NavigationController {

    //fields
    @Autowired
    private BookService bookService;
    @Autowired
    private BlogService blogService;

    //methods
    @RequestMapping("/")
    public String getIndexPage(Model model) {
        //last added books
        List<Book> lastAddedBooks = bookService.getLastAddedBooks();
        model.addAttribute("lastAddedBooks", lastAddedBooks);
        //random books
        List<Book> randomBooks = bookService.getRandomBooks();
        model.addAttribute("randomBooks", randomBooks);
        //last added blogs
        List<Blog> lastAddedBlogs = blogService.getLastAddedBlogs();
        model.addAttribute("lastAddedBlogs", lastAddedBlogs);
        return "view/index";
    }

    @RequestMapping("/shop")
    public String getShopPage(Model model, @RequestParam(name = "categories", required = false) String[] selectedCategories,
                              @RequestParam(name = "priceRange", required = false) String priceRange,
                              @RequestParam(name = "ageRange", required = false) String ageRange){
        //setting searchEntity class
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setCategories(selectedCategories);
        searchEntity.parseAndSetPriceRange(priceRange);
        searchEntity.parseAndSetAgeRange(ageRange);
        //categories
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);
        //books
        List<Book> books = bookService.getBooksByMultipleParameters(searchEntity);
        model.addAttribute("books", books);
        //searchEntity
        model.addAttribute("searchentity", searchEntity);
        return "view/shop";
    }

    @RequestMapping("/single-product")
    public String getSingleProductPage(Model model, @RequestParam(name = "idBook") Integer idBook){
        //get book by id
        Book book = bookService.getBookById(idBook);
        model.addAttribute("book", book);
        //upsell books
        List<Book> upsellBooks = bookService.getUpSellBooks();
        model.addAttribute("upsellBooks", upsellBooks);
        return "view/single-product";
    }

    @RequestMapping("/login")
    public String getLoginPage() {
        return "view/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "view/register";
    }

    @RequestMapping("/blog")
    public String getBlogPage(Model model)  {
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs",blogs);
        return "view/blog";
    }

    @GetMapping("/contact")
    public String getContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "view/contact";
    }

    @RequestMapping("/blog-details")
    public String getBlogDetailsPage() {
        return "view/blog-details";
    }

}
