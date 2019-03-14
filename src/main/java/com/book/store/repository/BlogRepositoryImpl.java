package com.book.store.repository;

import com.book.store.model.Blog;
import com.book.store.model.Comment;
import com.book.store.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogRepositoryImpl implements BlogRepository {

    public final String GET_ALL_BLOG_SQL = "SELECT * FROM blog;";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Blog> getAllBlog() {

        List<Blog> list = jdbcTemplate.query(GET_ALL_BLOG_SQL, new Object[]{Constants.BLOG_STATUS_ACTIVE}, new BeanPropertyRowMapper<>(Blog.class));

        return list;
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
