package com.book.store.service;

import com.book.store.model.Cart;
import com.book.store.model.Wishlist;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {

    List<Cart> getCartsByIdUser(int idUser);

    List<Wishlist> getWishlistsByIdUser(int idUser);

}
