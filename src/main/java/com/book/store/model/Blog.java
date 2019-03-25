package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class Blog {

    private int idBlog;
    private String title;
    private String desc;
    private LocalDateTime shareDate;
    private String imagePath;
    private List<BlogReview> blogReviewList;
    private User user;

    public Blog(){
        blogReviewList = new ArrayList<>();
    }

    public void addBlogReview(BlogReview blogReview){
        blogReviewList.add(blogReview);
    }



}
