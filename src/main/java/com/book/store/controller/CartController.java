package com.book.store.controller;

import com.book.store.model.Cart;
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

    @GetMapping("/get-carts")
    @ResponseBody
    public List<Cart> getCartByIdUser(){
        List<Cart> carts = cartService.getCartsByIdUser(5);
        return carts;
    }

    @RequestMapping("/delete-cart")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartById(@RequestParam("id") Integer id){
        cartService.deleteCartById(id);
        System.out.println(id);
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

    @PostMapping("/add-to-cart")
    public ResponseEntity addToCart(@RequestParam(name = "idBook") Integer idBook,
                                  @RequestParam(name = "idWishlist", required = false) Integer idWishlist) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.addToCart(user.getIdUser(), idBook, idWishlist);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add-to-wishlist")
    public ResponseEntity addToWishlist(@RequestParam(name = "idBook") Integer idBook) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.addToWishlist(user.getIdUser(), idBook);
        return new ResponseEntity(HttpStatus.OK);
    }

}
