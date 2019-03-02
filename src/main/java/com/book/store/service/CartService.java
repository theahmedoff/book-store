package com.book.store.service;

import com.book.store.model.Cart;
import com.book.store.model.Wishlist;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {

    List<Cart> getCartsByIdUser(int idUser);

    void deleteCartById(int id);

    List<Wishlist> getWishlistsByIdUser(int idUser);

    void deleteWishlistById(int idWishlist, int idUser);

    void addWishlistToCart(int idUser, int idBook, int idWishlist);

}
