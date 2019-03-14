package com.book.store.controller;

import com.book.store.model.Blog;
import com.book.store.model.Comment;
import com.book.store.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/blog/{id}/comment")
    public String getCommentByTopicId(@PathVariable("id") int id, Blog blog){
        List<Comment> list = blogService.getCommentByBlogId(id);
//        model.addAttribute("list", list);
        return "fragment/comments";
    }


}
