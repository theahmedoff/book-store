package com.book.store.util;

import com.book.store.model.Book;

public class BookUtil {

    public static void calculateDiscountedPrice(Book book) {
        double price = book.getStock().getPrice();
        int discount = book.getStock().getDiscount();

        double discountedPrice = price - ((price * discount) / 100);
        book.getStock().setDiscountedPrice(discountedPrice);
    }

}
