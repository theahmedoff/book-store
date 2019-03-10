package com.book.store.service;

import com.book.store.model.BillingInfo;
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
    public void deleteCartById(int id) {
        cartRepository.deleteCartById(id);
    }

    @Override
    public List<Wishlist> getWishlistsByIdUser(int idUser) {
        return cartRepository.getWishlistsByIdUser(idUser);
    }

    @Override
    public void deleteWishlist(int idBook, int idUser) {
        cartRepository.deleteWishlist(idBook, idUser);
    }

    @Override
    public void addToCart(int idUser, int idBook) {
        cartRepository.addToCart(idUser, idBook);
    }

    @Override
    public void addToWishlist(int idUser, int idBook) {
        cartRepository.addToWishlist(idUser, idBook);
    }

    @Override
    public void updateCart(int idUser, int idCart, int quantity) {
        cartRepository.updateCart(idUser, idCart, quantity);
    }

    @Override
    public BillingInfo getBillingInfo(int idUser) {
        return cartRepository.getBillingInfo(idUser);
    }
}
