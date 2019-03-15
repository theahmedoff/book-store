package com.book.store.controller;

import com.book.store.model.Book;
import com.book.store.model.Category;
import com.book.store.model.Review;
import com.book.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public List<Review> getReviewsByIdBook(@RequestParam(name = "idBook") Integer idBook) {
        List<Review> reviews = bookService.getReviewsByIdBook(idBook);
        return reviews;
    }

    @GetMapping("/get-all-books")
    public String getBooksByIdCategory(Model model) {
        List<Book> books = bookService.getAllBook();
        model.addAttribute("allBooks", books);
        return "fragments/tab-content";
    }


    @GetMapping("/get-book-by-category-type")
    @ResponseBody
    public List<Book> getAllBookByCategoryType(@RequestParam("cateType")String cateType){
        List<Book> books = bookService.getAllBookByCategoryType(cateType);

        return books;
    }


}
