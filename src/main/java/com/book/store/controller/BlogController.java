package com.book.store.controller;

import com.book.store.model.Blog;
import com.book.store.model.BlogReview;
import com.book.store.model.User;
import com.book.store.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {

    //fields
    @Autowired
    private BlogService blogService;

    //methods
    @RequestMapping("/blog-details/{idBlog}")
    public String getBlogById(@PathVariable("idBlog") int idBlog, Model model) {
        Blog blog = blogService.getBlogById(idBlog);
        model.addAttribute("blog", blog);
        return "view/blog-details";
    }

    @GetMapping("/get-reviews-by-idBlog")
    @ResponseBody
    public List<BlogReview> getReviewsByIdBlog(@RequestParam("idBlog") int idBlog) {
        List<BlogReview> blogReviews = blogService.getReviewsByIdBlog(idBlog);
        return blogReviews;
    }

    @PostMapping("/add-blog-review")
    public ResponseEntity addBlogReview(@RequestParam("idBlog") int idBlog,
                                        @RequestParam("desc") String desc) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BlogReview blogReview = new BlogReview();

        if (!desc.trim().isEmpty()) {
            Blog blog = new Blog();
            blog.setIdBlog(idBlog);
            blogReview.setDesc(desc);
            blogReview.setBlog(blog);
            blogReview.setUser(user);
            blogReview.setShareDate(LocalDateTime.now());

        } else {
            throw new RuntimeException();
        }

        blogService.addReview(blogReview);
        return new ResponseEntity(HttpStatus.OK);
    }

}
