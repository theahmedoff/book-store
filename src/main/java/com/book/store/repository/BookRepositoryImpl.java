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
    private static final String GET_ALL_LAST_BOOK_SQL = "select b.id_book, b.image_path, a.id_author, a.full_name, r.id_review, AVG(r.rating) as average_rating, s.id_stock, s.old_price, s.price FROM book b INNER JOIN author a on b.id_author = a.id_author INNER JOIN stock s on b.id_book = s.id_book LEFT JOIN review r on b.id_book = r.id_book GROUP BY b.id_book";

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
        List<Book> books = jdbcTemplate.query(GET_ALL_LAST_BOOK_SQL, new Object[]{}, new ResultSetExtractor<List<Book>>() {
            @Override
            public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Book> list = new ArrayList<>();
                while (rs.next()){
                    Book book = new Book();
                    book.setIdBook(rs.getInt("id_book"));
                    book.setImagePath(rs.getString("image_path"));
                    book.setAvarageRating(rs.getDouble("average_rating"));

                    Author a = new Author();
                    a.setIdAuthor(rs.getInt("id_author"));
                    a.setFullName(rs.getString("full_name"));
                    book.setAuthor(a);

                    Stock s = new Stock();
                    s.setIdStock(rs.getInt("id_stock"));
                    s.setPrice(rs.getDouble("price"));
                    book.setStock(s);

                    list.add(book);
                }
                return list;
            }
        });
        return books;
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(GET_ALL_CATEGORIES_SQL, new BeanPropertyRowMapper<>(Category.class));
    }

}
