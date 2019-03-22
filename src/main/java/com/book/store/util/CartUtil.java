package com.book.store.util;

import com.book.store.model.Cart;

import java.util.List;

public class CartUtil {

    public static double getSubtotal(List<Cart> carts) {
        double subTotal = 0;
        for(Cart cart : carts) {
            subTotal += cart.getSubTotals();
        }
        return subTotal;
    }

}
