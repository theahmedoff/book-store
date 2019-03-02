package com.book.store.controller;

import com.book.store.model.User;
import com.book.store.model.Wishlist;
import com.book.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("cart")
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




    @GetMapping("/get-wishlists")
    @ResponseBody
    public List<Wishlist> getWishlistsByIdUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Wishlist> wishlists = cartService.getWishlistsByIdUser(user.getIdUser());
        return wishlists;
    }

    @DeleteMapping("/delete-wishlist")
    public ResponseEntity deleteWishlistById(@RequestParam(name = "idWishlist") Integer idWishlist) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.deleteWishlistById(idWishlist, user.getIdUser());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add-wishlist-to-cart")
    public ResponseEntity addCart(@RequestParam(name = "idUser") Integer idUser,
                                  @RequestParam(name = "idBook") Integer idBook,
                                  @RequestParam(name = "idWishlist") Integer idWishlist) {
        cartService.addWishlistToCart(idUser, idBook, idWishlist);
        return new ResponseEntity(HttpStatus.OK);
    }

}
