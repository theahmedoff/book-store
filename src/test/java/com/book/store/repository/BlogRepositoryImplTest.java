package com.book.store.repository;

import com.book.store.model.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogRepositoryImplTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void getAllBlog() {
     List<Blog> list = blogRepository.getAllBlogs();
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    public void getBlogById() {
        Blog blog = blogRepository.getBlogById(1);
        System.out.println(blog);
    }

    @Test
    public void addBlog() {
        Blog blog = new Blog();
        blog.setTitle("title");
        blog.setDesc("description");
        blog.setShareDate(LocalDateTime.now());

        blogRepository.addBlog(blog);
    }

    @Test
    public void getCommentByBlogId() {


    }

    @Test
    public void addComment() {
    }
}