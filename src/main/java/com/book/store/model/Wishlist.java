package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

    private int idWishlist;
    private User user;
    private Book book;

}
