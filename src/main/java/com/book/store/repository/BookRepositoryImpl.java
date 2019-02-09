package com.book.store.repository;

import com.book.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {

    //fields
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_CATEGORIES_SQL = "select * from category";
    private static final String GET_BOOKS_BY_MULTIPLE_PARAMETER_SQL = "select * from (select b.id_book,b.title, b.desc, b.image_path, b.language, b.write_date, avg(r.rating) as average_rating, b.id_author from book b left join review r on b.id_book=r.id_book group by b.id_book) book inner join author a on book.id_author=a.id_author inner join stock s on book.id_book=s.id_book";
    private static final String GET_ALL_LAST_BOOK_SQL = "SELECT s.id_stock, s.last_added_date, s.quantity, s.price, b.id_book, b.image_path, b.title, a.id_author, a.full_name FROM stock s INNER JOIN book b ON s.id_book = b.id_book INNER JOIN author a ON b.id_author = a.id_author ORDER BY s.last_added_date DESC LIMIT 6";

    //methods
    @Override
    public List<Book> getBooksByMultipleParameters(Integer idCategory) {
        List<Book> books = jdbcTemplate.query(GET_BOOKS_BY_MULTIPLE_PARAMETER_SQL, new Object[]{}, new ResultSetExtractor<List<Book>>() {

            @Override
            public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Book> list = new ArrayList<>();

                while (rs.next()) {
                    Book book = new Book();
                    book.setIdBook(rs.getInt("book.id_book"));
                    book.setTitle(rs.getString("book.title"));
                    book.setDesc(rs.getString("book.desc"));
                    book.setImagePath(rs.getString("book.image_path"));
                    book.setLanguage(rs.getString("book.language"));
                    book.setWriteDate(rs.getDate("book.write_date").toLocalDate());
                    book.setAvarageRating(rs.getDouble("book.average_rating"));

                    Author author = new Author();
                    author.setIdAuthor(rs.getInt("id_author"));
                    author.setFullName(rs.getString("full_name"));
                    book.setAuthor(author);

                    Stock stock = new Stock();
                    stock.setIdStock(rs.getInt("id_stock"));
                    stock.setQuantity(rs.getInt("quantity"));
                    stock.setPrice(rs.getDouble("price"));
                    stock.setOldPrice(rs.getDouble("old_price"));
                    stock.setLastAddedDate(rs.getTimestamp("last_added_date").toLocalDateTime());
                    book.setStock(stock);

                    list.add(book);
                }

                return list;
            }
        });

        return books;
    }

    @Override
    public List<Book> getLastBooks() {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(GET_ALL_CATEGORIES_SQL, new BeanPropertyRowMapper<>(Category.class));
    }

}
