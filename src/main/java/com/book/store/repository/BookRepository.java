package com.book.store.repository;

import com.book.store.model.Book;

import com.book.store.model.Category;
import com.book.store.model.Review;
import com.book.store.model.SearchEntity;


import java.util.List;

public interface BookRepository {

    List<Book> getBooksByMultipleParameters(SearchEntity searchEntity);

    List<Book> getLastAddedBooks();

    List<Book> getUpSellBooks();

    List<Book> getAllBooks();

    List<Book> getBooksByCategoryType(String cateType);

    List<Category> getAllCategories();

    Book getBookById(int idBook);

    List<Review> getReviewsByIdBook(int idBook);

    void addReview(Review review);

}
