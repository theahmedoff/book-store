package com.book.store.service;

import com.book.store.model.Blog;
import com.book.store.model.Comment;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlog();

    Blog getBlogById(int id);

    void addBlog(Blog blog);

    List<Comment> getCommentByBlogId(int id);

    void addComment(Comment comment);

}
