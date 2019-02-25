package com.book.store.controller;

import com.book.store.model.Wishlist;
import com.book.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String  getWishlistPage(Model model) {
        //TODO: get id_user and set to method
        List<Wishlist> wishlists = cartService.getWishlistsByIdUser(3);
        model.addAttribute("wishlists", wishlists);
        return "view/wishlist";
    }

    @GetMapping("/checkout")
    public String getCheckoutPage() {
        return "view/checkout";
    }

}
