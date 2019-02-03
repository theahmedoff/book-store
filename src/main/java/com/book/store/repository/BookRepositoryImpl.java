package com.book.store.repository;

import com.book.store.model.Book;
import com.book.store.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public class BookRepositoryImpl implements BookRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_CATEGORIES_SQL="select * from category";


<<<<<<< HEAD
=======
    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public List<Book> getBooksByIdCategory(int id) {
        return null;


    }
>>>>>>> fcbbd86b6f1a0bfb45b356314054403052630d2b

    @Override
    public List<Book> getBooksByMultipleParameters() {
        return null;
    }
<<<<<<< HEAD
=======

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(GET_ALL_CATEGORIES_SQL,new BeanPropertyRowMapper<>(Category.class));
    }


>>>>>>> fcbbd86b6f1a0bfb45b356314054403052630d2b
}
