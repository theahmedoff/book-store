package com.book.store.controller;

import com.book.store.model.Book;
import com.book.store.model.Category;
import com.book.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NavigationController {

    //fields
    @Autowired
    private BookService bookService;

    //methods
    @RequestMapping("/")
    public String getIndexPage() {
        return "view/index";
    }

    @RequestMapping("/shop")
    public String getShopPage(@RequestParam(value = "idCategory", required = false) Integer idCategory, Model model){
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Book> books = bookService.getBooksByMultipleParameters(idCategory);
        System.out.println(books);
        model.addAttribute("books", books);

        return "view/shop";
    }

    @RequestMapping("/single-product")
    public String getSingleProductPage(){
        return "view/single-product";
    }

    @RequestMapping("/login")
    public String getLoginPage() {
        return "view/login";
    }

    @RequestMapping("/register")
    public String getRegisterPage() {
        return "view/register";
    }

    @RequestMapping("/wishlist")
    public String getWishlistPage() {
        return "view/wishlist";
    }

    @RequestMapping("/team")
    public String getTeamPage() {
        return "view/team";
    }

    @RequestMapping("/faq")
    public String getFaqPage() {
        return "view/faq";
    }

    @RequestMapping("/cart")
    public String getCartPage() {
        return "view/cart";
    }

    @RequestMapping("/blog")
    public String getBlogPage() {
        return "view/blog";
    }

    @RequestMapping("/about")
    public String getAboutPage() {
        return "view/about";
    }

    @RequestMapping("/contact")
    public String getContactPage() {
        return "view/contact";
    }

    @RequestMapping("/blog-details")
    public String getBlogDetailsPage() {
        return "view/blog-details";
    }

}
