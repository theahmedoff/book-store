package com.book.store.repository;

import com.book.store.model.Book;

import com.book.store.model.Category;
import com.book.store.model.SearchEntity;


import java.util.List;

public interface BookRepository {

    List<Book> getBooksByMultipleParameters(SearchEntity searchEntity);

    List<Book> getLastBooks();

    List<Category> getAllCategories();

}
