package com.book.store.service;

import com.book.store.model.Blog;
import com.book.store.model.BlogReview;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogs();

    List<Blog> getLastAddedBlogs();

    Blog getBlogById(int id);

    void addBlog(Blog blog);

    List<BlogReview> getReviewsByIdBlog(int id);

    void addReview(BlogReview blogReview);

}
