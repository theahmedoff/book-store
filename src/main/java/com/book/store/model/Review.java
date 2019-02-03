package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int idReview;
    private String desc;
    private LocalDate writeDate;
    private double rating;
    private User user;
    private Book book;
}
