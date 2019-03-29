package com.book.store.service;

import com.book.store.model.Book;
import com.book.store.model.Category;
import com.book.store.model.Review;
import com.book.store.model.SearchEntity;

import java.util.List;

public interface BookService {

    List<Book> getBooksByMultipleParameters(SearchEntity searchEntity);

    List<Book> getLastAddedBooks();

    List<Book> getRandomBooks();

    List<Book> getUpSellBooks();

    List<Category> getAllCategories();

    Book getBookById(int idBook);

    List<Review> getReviewsByIdBook(int idBook);

    void addReview(Review review);

}
