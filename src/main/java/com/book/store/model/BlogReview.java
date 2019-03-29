package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogReview {

    private int idBlogReview;
    private String desc;
    private LocalDateTime shareDate;
    private Blog blog;
    private User user;

}
