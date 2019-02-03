package com.book.store.repository;

import com.book.store.model.Book;
import com.book.store.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    //fields
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_CATEGORIES_SQL = "select * from category";


    //methods
    @Override
    public List<Book> getLastBooks() {
        return null;
    }

    @Override
    public List<Book> getBooksByMultipleParameters() {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(GET_ALL_CATEGORIES_SQL, new BeanPropertyRowMapper<>(Category.class));
    }

}
