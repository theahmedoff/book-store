package com.book.store.service;

import com.book.store.model.BillingInfo;
import com.book.store.model.Cart;
import com.book.store.model.Wishlist;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {

    List<Cart> getCartsByIdUser(int idUser);

    void deleteCartById(int id);

    List<Wishlist> getWishlistsByIdUser(int idUser);

    void deleteWishlist(int idBook, int idUser);

    void addToCart(int idUser, int idBook);

    void addToWishlist(int idUser, int idBook);

    void updateCart(int idUser, int idCart, int quantity);

    BillingInfo getBillingInfo(int idUser);

}
