package com.book.store.repository;

import com.book.store.model.Book;
import com.book.store.model.Category;

import java.util.List;

public interface BookRepository {

     List<Book> getAllBooks();
     List<Book> getBooksByIdCategory(int id);
     List<Book> getLastBooks();
     List<Category> getAllCategories();


}
