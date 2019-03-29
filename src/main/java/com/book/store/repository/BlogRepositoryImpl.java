package com.book.store.repository;

import com.book.store.model.Blog;
import com.book.store.model.BlogReview;
import com.book.store.model.User;
import com.book.store.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BlogRepositoryImpl implements BlogRepository {

    //fields
    private static final String GET_ALL_BLOGS_SQL = "select b.id_blog, b.title, b.desc, b.share_date, b.image_path, u.id_user, u.name, u.surname from blog b inner join user u on b.id_user = u.id_user";
    private static final String GET_LAST_ADDED_BLOGS_SQL = "select b.id_blog, b.title, b.desc, b.share_date, b.image_path, u.id_user, u.name, u.surname from blog b inner join user u on b.id_user = u.id_user order by share_date desc limit 3";
    private static final String GET_BLOG_BY_ID_SQL = "select b.id_blog, b.title, b.desc, b.share_date, b.image_path, u.id_user, u.name, u.surname from blog b inner join user u on b.id_user = u.id_user where b.id_blog = ?";
    private static final String ADD_BLOG_SQL = "insert into blog (title, desc, share_date, image_path, id_user) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_REVIEWS_BY_ID_SQL = "select br.id_blog_review, br.desc, br.share_date, u.id_user, u.name, u.surname from blog_review br inner join user u on br.id_user = u.id_user where br.id_blog = ?";
    private static final String ADD_BLOG_REVIEW_SQL = "insert into blog_review(`desc`, `share_date`, `id_blog`, `id_user`) values(?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    //methods
    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = jdbcTemplate.query(GET_ALL_BLOGS_SQL, new ResultSetExtractor<List<Blog>>() {
            @Override
            public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Blog> list = new LinkedList<>();
                while (rs.next()){
                    Blog blog = new Blog();
                    blog.setIdBlog(rs.getInt("b.id_blog"));
                    blog.setTitle(rs.getString("b.title"));
                    blog.setDesc(rs.getString("b.desc"));
                    blog.setShareDate(rs.getTimestamp("b.share_date").toLocalDateTime());
                    blog.setImagePath(rs.getString("b.image_path"));

                    User user = new User();
                    user.setIdUser(rs.getInt("u.id_user"));
                    user.setName(rs.getString("u.name"));
                    user.setSurname(rs.getString("u.surname"));
                    blog.setUser(user);

                    list.add(blog);
                }
                return list;
            }
        });
        return blogs;
    }

    @Override
    public List<Blog> getLastAddedBlogs() {
        List<Blog> blogs = jdbcTemplate.query(GET_LAST_ADDED_BLOGS_SQL, new ResultSetExtractor<List<Blog>>() {
            @Override
            public List<Blog> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Blog> list = new LinkedList<>();
                while (rs.next()){
                    Blog blog = new Blog();
                    blog.setIdBlog(rs.getInt("b.id_blog"));
                    blog.setTitle(rs.getString("b.title"));
                    blog.setDesc(rs.getString("b.desc"));
                    blog.setShareDate(rs.getTimestamp("b.share_date").toLocalDateTime());
                    blog.setImagePath(rs.getString("b.image_path"));

                    User user = new User();
                    user.setIdUser(rs.getInt("u.id_user"));
                    user.setName(rs.getString("u.name"));
                    user.setSurname(rs.getString("u.surname"));
                    blog.setUser(user);

                    list.add(blog);
                }
                return list;
            }
        });
        return blogs;
    }

    @Override
    public Blog getBlogById(int id) {
        Blog blog = jdbcTemplate.queryForObject(GET_BLOG_BY_ID_SQL, new Object[]{id}, new RowMapper<Blog>() {
            @Override
            public Blog mapRow(ResultSet rs, int i) throws SQLException {
                Blog blog = new Blog();
                blog.setIdBlog(rs.getInt("b.id_blog"));
                blog.setTitle(rs.getString("b.title"));
                blog.setDesc(rs.getString("b.desc"));
                blog.setShareDate(rs.getTimestamp("b.share_date").toLocalDateTime());
                blog.setImagePath(rs.getString("b.image_path"));

                User user = new User();
                user.setIdUser(rs.getInt("u.id_user"));
                user.setName(rs.getString("u.name"));
                user.setSurname(rs.getString("u.surname"));
                blog.setUser(user);
                return blog;
            }
        });
        return blog;
    }

    @Override
    public void addBlog(Blog blog) {
        jdbcTemplate.update(ADD_BLOG_SQL, blog.getTitle(), blog.getDesc(), blog.getShareDate(), blog.getImagePath(), blog.getUser().getIdUser());
    }

    @Override
    public List<BlogReview> getReviewsByIdBlog(int id) {
        List<BlogReview> reviews = jdbcTemplate.query(GET_REVIEWS_BY_ID_SQL, new Object[]{id}, new ResultSetExtractor<List<BlogReview>>() {
            @Override
            public List<BlogReview> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<BlogReview> list = new LinkedList<>();
                while (rs.next()) {
                    BlogReview blogReview = new BlogReview();
                    blogReview.setIdBlogReview(rs.getInt("br.id_blog_review"));
                    blogReview.setDesc(rs.getString("br.desc"));
                    blogReview.setShareDate(rs.getTimestamp("br.share_date").toLocalDateTime());
                    User user = new User();
                    user.setIdUser(rs.getInt("u.id_user"));
                    user.setName(rs.getString("u.name"));
                    user.setSurname(rs.getString("u.surname"));
                    blogReview.setUser(user);

                    list.add(blogReview);
                }
                return list;
            }
        });
        return reviews;
    }

    @Override
    public void addReview(BlogReview blogReview) {
        int affectedRows = jdbcTemplate.update(ADD_BLOG_REVIEW_SQL, blogReview.getDesc(), blogReview.getShareDate(), blogReview.getBlog().getIdBlog(), blogReview.getUser().getIdUser());
        if (affectedRows == 0) {
            throw new RuntimeException();
        }
    }

}
