package com.book.store.controller;

import com.book.store.model.Book;
import com.book.store.model.Category;
import com.book.store.model.Review;
import com.book.store.model.User;
import com.book.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Controller
public class BookController {

    //fields
    @Autowired
    BookService bookService;

    //methods
    @RequestMapping("/categories")
    @ResponseBody
    public List<Category> getAllCategories() {
        List<Category> categories = bookService.getAllCategories();
        return categories;
    }

    @RequestMapping("/reviews")
    public String getReviewsByIdBook(@RequestParam(name = "idBook") Integer idBook, Model model) {
        List<Review> reviews = bookService.getReviewsByIdBook(idBook);
        model.addAttribute("reviews", reviews);
        return "fragments/book-review-rating";
    }

    @RequestMapping("/add-review")
    @ResponseBody
    public ResponseEntity addreview(@RequestParam(name = "idBook") Integer idBook,
                                    @RequestParam(name = "desc") String desc,
                                    @RequestParam(name = "rating") Integer rating) {
        //book
        Book book = new Book();
        book.setIdBook(idBook);
        //user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (desc.trim().isEmpty()) {
            throw new RuntimeException();
        }
        //review
        Review review = new Review();
        review.setDesc(desc.trim());
        review.setWriteDate(LocalDateTime.now());
        review.setRating(rating);
        review.setBook(book);
        review.setUser(user);

        bookService.addReview(review);
        return new ResponseEntity(HttpStatus.OK);
    }

}
