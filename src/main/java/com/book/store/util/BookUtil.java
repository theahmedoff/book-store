package com.book.store.util;

import com.book.store.model.Book;

import java.math.BigDecimal;

public class BookUtil {

    public static double calculateDiscountedPrice(int discount, double price) {
        BigDecimal bigDecimal = new BigDecimal(price);
        BigDecimal bd2 = bigDecimal.multiply(new BigDecimal(discount)).divide(new BigDecimal(100));
        BigDecimal result = bd2.setScale(0, 4);
        return bigDecimal.subtract(result).doubleValue();
    }

}
