package com.book.store.service;

import com.book.store.model.Book;
import com.book.store.model.Category;
import com.book.store.model.SearchEntity;
import com.book.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    //fields
    private BookRepository bookRepository;

    //constructors
    @Autowired
    public BookServiceImpl (BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //methods
    @Override
    public List<Book> getBooksByMultipleParameters(SearchEntity searchEntity) {
        return bookRepository.getBooksByMultipleParameters(searchEntity);
    }

    @Override
    public List<Book> getLastBooks() {
        return bookRepository.getLastBooks();
    }

    @Override
    public List<Category> getAllCategories() {
        return bookRepository.getAllCategories();
    }

}
