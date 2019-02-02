package com.book.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

    /*
    * Sehifeleri yoxlamaq ucun her sehifenin linkini elave etmiwem..
    * blog.html ve blog-details.html ile cox vaxt itirmedim Bu sehifeleri sonda vaxt qalsa hell ederik..
    *
    * */

    @RequestMapping("/")
    public String getIndexPage() {
        return "view/index";
    }

    @RequestMapping("/shop")
    public String getShopPage(){
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
