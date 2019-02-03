package com.book.store.repository;

import com.book.store.model.Book;
<<<<<<< HEAD
import com.sun.source.tree.LambdaExpressionTree;
=======
import com.book.store.model.Category;
>>>>>>> fcbbd86b6f1a0bfb45b356314054403052630d2b

import java.util.List;

public interface BookRepository {

<<<<<<< HEAD
    public List<Book> getBooksByMultipleParameters();
=======
     List<Book> getAllBooks();
     List<Book> getBooksByIdCategory(int id);
     List<Book> getLastBooks();
     List<Category> getAllCategories();


>>>>>>> fcbbd86b6f1a0bfb45b356314054403052630d2b
}
