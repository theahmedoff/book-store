package com.book.store.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Blog {
    private int id;

    private String title;

    private String description;

    private LocalDateTime shareDate;

    private List<Comment> commentList;

    public Blog(){
        commentList = new ArrayList<>();
    }

    public void addComment(Comment comment){
        commentList.add(comment);
    }



}