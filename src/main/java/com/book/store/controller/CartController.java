package com.book.store.controller;

import com.book.store.model.Wishlist;
import com.book.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    //fields
    @Autowired
    CartService cartService;

    //methods
    @GetMapping
    public String getCartPage() {
        return "view/cart";
    }

    @GetMapping("/wishlist")
    public String getWishlistPage() {
        return "view/wishlist";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage() {
        return "view/checkout";
    }

    @GetMapping("/wishlists")
    @ResponseBody
    public List<Wishlist> getWishlistsByIdUser() {
        //TODO: get idUser and set it to metod
        List<Wishlist> wishlists = cartService.getWishlistsByIdUser(3);
        return wishlists;
    }

}
