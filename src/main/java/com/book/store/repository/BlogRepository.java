package com.book.store.repository;

import com.book.store.model.Blog;
import com.book.store.model.BlogReview;

import java.util.List;

public interface BlogRepository {

    List<Blog> getAllBlogs();

    List<Blog> getLastAddedBlogs();

    Blog getBlogById(int id);

    void addBlog(Blog blog);

    List<BlogReview> getReviewByIdBlog(int id);

    void addReview(BlogReview blogReview);

}
