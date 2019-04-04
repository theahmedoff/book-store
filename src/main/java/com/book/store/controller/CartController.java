package com.book.store.controller;

import com.book.store.model.*;
import com.book.store.service.CartService;
import com.book.store.util.BookUtil;
import com.book.store.util.CartUtil;
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
    public String getCheckoutPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //billing info
        BillingInfo billingInfo = cartService.getBillingInfo(user.getIdUser());
        model.addAttribute("billingInfo", billingInfo);
        //carts
        List<Cart> carts = cartService.getCartsById(user.getIdUser());
        model.addAttribute("carts", carts);
        return "view/checkout";
    }

    @GetMapping("/get-carts")
    @ResponseBody
    public List<Cart> getCartByIdUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Cart> carts = cartService.getCartsById(user.getIdUser());
        return carts;
    }

    @GetMapping("/get-wishlists")
    @ResponseBody
    public List<Wishlist> getWishlistsByIdUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Wishlist> wishlists = cartService.getWishlistsByIdUser(user.getIdUser());
        return wishlists;
    }

    @RequestMapping("/delete-cart")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartById(@RequestParam("id") Integer id){
        cartService.deleteCartById(id);
    }

    @DeleteMapping("/delete-wishlist")
    public ResponseEntity deleteWishlistById(@RequestParam(name = "idBook") Integer idBook) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.deleteWishlist(idBook, user.getIdUser());
        user.removeWishlist(idBook);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity addToCart(@RequestParam(name = "idBook") Integer idBook,
                                    @RequestParam(name = "idWishlist", required = false) Integer idWishlist,
                                    @RequestParam(name = "quantity", required = false) String quantity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Integer qty;
        if (quantity != null && !quantity.trim().isEmpty()) {
            qty = Integer.parseInt(quantity);
        } else {
            qty = 1;
        }
        cartService.addToCart(user.getIdUser(), idBook, idWishlist, qty);
        user.removeWishlist(idBook);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add-to-wishlist")
    public ResponseEntity addToWishlist(@RequestParam(name = "idBook") Integer idBook) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.addToWishlist(user.getIdUser(), idBook);
        user.addWishlist(idBook);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update-cart")
    @ResponseStatus(HttpStatus.OK)
    public void updateCart(@RequestParam(name = "idCart") int idCart,
                                     @RequestParam(name = "quantity") int quantity) {
        cartService.updateCart(idCart, quantity);
    }

    @PostMapping("/checkout")
    public String updateBillingInfo(@ModelAttribute(name = "billingInfo") BillingInfo billingInfo) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BillingInfo updatedBillingInfo = cartService.updateBillingInfo(user.getIdUser(), billingInfo);
        return "redirect:/cart/invoice";
    }

}
