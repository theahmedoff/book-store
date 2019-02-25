package com.book.store.service;

import com.book.store.model.Cart;
import com.book.store.model.Wishlist;
import com.book.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    //fields
    @Autowired
    private CartRepository cartRepository;

    //methods
    @Override
    public List<Cart> getCartsByIdUser(int idUser) {
        return cartRepository.getCartsByIdUser(idUser);
    }

    @Override
    public List<Wishlist> getWishlistsByIdUser(int idUser) {
        return cartRepository.getWishlistsByIdUser(idUser);
    }
}
