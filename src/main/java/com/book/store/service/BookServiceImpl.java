package com.book.store.service;

import com.book.store.model.Book;
import com.book.store.model.Category;
import com.book.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl (BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }


    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> getBooksByIdCategory(int id) {
        return null;
    }

    @Override
    public List<Book> getLastBooks() {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return bookRepository.getAllCategories();
    }
}
