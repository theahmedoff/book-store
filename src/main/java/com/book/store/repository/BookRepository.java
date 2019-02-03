package com.book.store.repository;

import com.book.store.model.Book;

import com.book.store.model.Category;


import java.util.List;

public interface BookRepository {

    List<Book> getBooksByMultipleParameters();

    List<Book> getLastBooks();

    List<Category> getAllCategories();

}
