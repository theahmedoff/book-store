package com.book.store.service;


import com.book.store.model.Blog;
import com.book.store.model.Comment;
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
    public List<Blog> getAllBlog() {
        return null;
    }

    @Override
    public Blog getBlogById(int id) {
        return null;
    }

    @Override
    public void addBlog(Blog blog) {

    }

    @Override
    public List<Comment> getCommentByBlogId(int id) {
        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }
}
