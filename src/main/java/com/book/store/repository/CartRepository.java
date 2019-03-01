package com.book.store.repository;

import com.book.store.model.Cart;
import com.book.store.model.Wishlist;

import java.util.List;

public interface CartRepository {

    List<Cart> getCartsByIdUser(int idUser);

    List<Wishlist> getWishlistsByIdUser(int idUser);

    void deleteWishlistById(int idWishlist);

    void addWishlistToCart(int idUser, int idBook);

}
