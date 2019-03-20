package com.book.store.repository;

import com.book.store.model.Blog;
import com.book.store.model.Book;
import com.book.store.model.Comment;

import java.util.List;

public interface BlogRepository {

    List<Blog> getAllBlog();

    Blog getBlogById(int id);

    void addBlog(Blog blog);


    List<Comment> getCommentByBlogId(int id);

    void addComment(Comment comment);

}
