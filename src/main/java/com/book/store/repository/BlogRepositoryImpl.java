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

    public final String GET_ALL_BLOG_SQL = "SELECT * FROM blog where status = ?";
    public final String GET_BLOG_BY_ID_SQL = "select * from blog where id=? and status=?";
    private static final String ADD_BLOG_SQL = "insert into blog (title, description, share_date, status) VALUES (?,?,?,?)";


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Blog> getAllBlog() {

        List<Blog> list = jdbcTemplate.query(GET_ALL_BLOG_SQL, new Object[]{Constants.BLOG_STATUS_ACTIVE}, new BeanPropertyRowMapper<>(Blog.class));

        return list;
    }

    @Override
    public Blog getBlogById(int id) {
        Blog blog = jdbcTemplate.queryForObject(GET_BLOG_BY_ID_SQL,new Object[]{id, Constants.BLOG_STATUS_ACTIVE}, new BeanPropertyRowMapper<>(Blog.class) );
        return blog;
    }

    @Override
    public void addBlog(Blog blog) {
        jdbcTemplate.update(ADD_BLOG_SQL,blog.getTitle(),blog.getDescription(),blog.getShareDate(),Constants.BLOG_STATUS_ACTIVE);
    }

    @Override
    public List<Comment> getCommentByBlogId(int id) {

        return null;
    }

    @Override
    public void addComment(Comment comment) {

    }
}
