package com.book.store.repository;

import com.book.store.model.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> getAllCartListByUserId(String email);
}
