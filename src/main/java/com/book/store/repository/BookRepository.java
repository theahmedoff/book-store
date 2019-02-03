package com.book.store.repository;

import com.book.store.model.Book;

import java.util.List;

public interface BookRepository {

    public List<Book> getAllBooks();
    public List<Book> getBooksByIdCategory(int id);
    public List<Book> getLastBooks();

}
