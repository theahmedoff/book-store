package com.book.store.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cart {

    private int idCart;
    private User user;
    private Book book;
    private int quantity;

    public double getSubTotals(){
        return book.getStock().getDiscountedPrice() * quantity;
    }



}
