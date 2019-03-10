package com.book.store.repository;

import com.book.store.model.BillingInfo;
import com.book.store.model.Cart;
import com.book.store.model.Wishlist;

import java.util.List;

public interface CartRepository {

    List<Cart> getCartsByIdUser(int idUser);

    void deleteCartById(int id);

    List<Wishlist> getWishlistsByIdUser(int idUser);

    void deleteWishlist(int idBook, int idUser);

    void addToCart(int idUser, int idBook);

    void addToWishlist(int idUser, int idBook);

    void updateCart(int idUser, int idCart, int quantity);

    BillingInfo getBillingInfo(int idUser);

}
