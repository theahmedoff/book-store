package com.book.store.service;

import com.book.store.model.Book;
import com.book.store.model.Category;

import java.util.List;

public interface BookService {

    List<Book> getBooksByMultipleParameters();

    List<Book> getLastBooks();

    List<Category> getAllCategories();

}
