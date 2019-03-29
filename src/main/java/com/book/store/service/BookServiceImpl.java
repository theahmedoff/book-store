package com.book.store.service;

import com.book.store.model.Book;
import com.book.store.model.Category;
import com.book.store.model.Review;
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
    public List<Book> getLastAddedBooks() {
        return bookRepository.getLastAddedBooks();
    }

    @Override
    public List<Book> getRandomBooks() {
        return bookRepository.getRandomBooks();
    }

    @Override
    public List<Book> getUpSellBooks() {
        return bookRepository.getUpSellBooks();
    }

    @Override
    public List<Category> getAllCategories() {
        return bookRepository.getAllCategories();
    }

    @Override
    public Book getBookById(int idBook) {
        return bookRepository.getBookById(idBook);
    }

    @Override
    public List<Review> getReviewsByIdBook(int idBook) {
        return bookRepository.getReviewsByIdBook(idBook);
    }

    @Override
    public void addReview(Review review) {
        bookRepository.addReview(review);
    }

}
