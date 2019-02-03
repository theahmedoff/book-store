package com.book.store.repository;

import com.book.store.model.Book;
import com.sun.source.tree.LambdaExpressionTree;

import java.util.List;

public interface BookRepository {

    public List<Book> getBooksByMultipleParameters();
}
