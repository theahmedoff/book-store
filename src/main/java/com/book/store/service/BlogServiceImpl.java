package com.book.store.service;


import com.book.store.model.Blog;
import com.book.store.model.BlogReview;
import com.book.store.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository){
        this.blogRepository=blogRepository;
    }


    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.getAllBlogs();
    }

    @Override
    public List<Blog> getLastAddedBlogs() {
        return blogRepository.getLastAddedBlogs();
    }

    @Override
    public Blog getBlogById(int id) {
        return blogRepository.getBlogById(id);
    }

    @Override
    public void addBlog(Blog blog) {
        blogRepository.addBlog(blog);
    }

    @Override
    public List<BlogReview> getReviewsByIdBlog(int id) {
        return blogRepository.getReviewsByIdBlog(id);
    }

    @Override
    public void addReview(BlogReview blogReview) {
        blogRepository.addReview(blogReview);
    }
}
