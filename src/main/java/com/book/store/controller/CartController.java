package com.book.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping
    public String getCartPage() {
        return "view/cart";
    }

    @RequestMapping("/wishlist")
    public String  getWishlistPage() {
        return "view/wishlist";
    }

    @RequestMapping("/checkout")
    public String getCheckoutPage() {
        return "view/checkout";
    }

}
