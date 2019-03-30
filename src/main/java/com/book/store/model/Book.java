package com.book.store.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    private int idBook;
    private String title;
    private String desc;
    private String firstImagePath;
    private String secondImagePath;
    private String language;
    private LocalDate writeDate;
    private int avarageRating;
    private Author author;
    private List<Category> categoryList;
    private List<Review> reviewList;
    private Stock stock;


    public Book() {
        this.categoryList = new ArrayList<>();
        this.reviewList = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categoryList.add(category);
    }

    public void addReview(Review review) {
        reviewList.add(review);
    }



}
