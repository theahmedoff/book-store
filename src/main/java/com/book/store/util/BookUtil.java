package com.book.store.util;

import com.book.store.model.Book;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BookUtil {

    public static double calculateDiscountedPrice(int discount, double price) {
        //price
        BigDecimal bigDecimal = new BigDecimal(Double.toString(price));
        //(price * discount) / 100
        BigDecimal result = bigDecimal.multiply(new BigDecimal(discount)).divide(new BigDecimal(100));
        BigDecimal roundedValue = result.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.subtract(roundedValue).doubleValue();
    }

}
