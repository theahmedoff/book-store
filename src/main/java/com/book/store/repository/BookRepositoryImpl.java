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
    private static final String GET_ALL_BOOKS_SQL = "select b.id_book, b.title, b.image_path, b.language, b.write_date as book_write_date, a.id_author, a.full_name, s.id_stock, s.quantity, s.price, s.last_added_date, c.id_category, c.type, r.id_review, r.desc, r.write_date as review_write_date, r.rating from book b inner join author a on b.id_author=a.id_author inner join book_category bc on b.id_book=bc.id_book inner join category c on bc.id_category=c.id_category inner join stock s on b.id_book=s.id_book left join review r on b.id_book=r.id_book";
    private static final String GET_ALL_LAST_BOOK_SQL = "SELECT s.id_stock, s.last_added_date, s.quantity, s.price, b.id_book, b.image_path, b.title, a.id_author, a.full_name FROM stock s INNER JOIN book b ON s.id_book = b.id_book INNER JOIN author a ON b.id_author = a.id_author ORDER BY s.last_added_date DESC LIMIT 6";

    //methods
    @Override
    public List<Book> getBooksByMultipleParameters(Integer idCategory) {
        List<Book> books = jdbcTemplate.query(GET_ALL_BOOKS_SQL, new Object[]{}, new ResultSetExtractor<List<Book>>() {

            @Override
            public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Book> list;
                Map<Integer, Book> bookMap = new LinkedHashMap<>();
                Map<Integer, Category> categoryMap = new LinkedHashMap<>();
                Map<Integer, Review> reviewMap = new LinkedHashMap<>();

                while (rs.next()) {
                    Book book = bookMap.get(rs.getInt("id_book"));

                    if (book == null) {
                        book = new Book();
                        book.setIdBook(rs.getInt("id_book"));
                        book.setTitle(rs.getString("title"));
                        book.setImagePath(rs.getString("image_path"));
                        book.setLanguage(rs.getString("language"));
                        book.setWriteDate(rs.getDate("book_write_date").toLocalDate());

                        Author author = new Author();
                        author.setIdAuthor(rs.getInt("id_author"));
                        author.setFullName(rs.getString("full_name"));
                        book.setAuthor(author);

                        Stock stock = new Stock();
                        stock.setIdStock(rs.getInt("id_stock"));
                        stock.setQuantity(rs.getInt("quantity"));
                        stock.setPrice(rs.getDouble("price"));
                        stock.setLastAddedDate(rs.getTimestamp("last_added_date").toLocalDateTime());
                        book.setStock(stock);

                        bookMap.put(book.getIdBook(), book);
                    }

                    if (categoryMap.get(rs.getInt("id_category")) == null) {
                        Category category = new Category();
                        category.setIdCategory(rs.getInt("id_category"));
                        category.setType(rs.getString("type"));
                        book.addCategory(category);
                        categoryMap.put(category.getIdCategory(), category);
                    }

                    if (reviewMap.get(rs.getInt("id_review")) == null & rs.getInt("id_review") != 0) {
                        Review review = new Review();
                        review.setIdReview(rs.getInt("id_review"));
                        review.setDesc(rs.getString("desc"));
                        review.setWriteDate(rs.getTimestamp("review_write_date").toLocalDateTime());
                        review.setRating(rs.getDouble("rating"));
                        review.setBook(book);
                        reviewMap.put(review.getIdReview(), review);
                    }
                }

                list = new ArrayList<>(bookMap.values());
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
                    book.setTitle(rs.getString("title"));

                    Author a = new Author();
                    a.setIdAuthor(rs.getInt("id_author"));
                    a.setFullName(rs.getString("full_name"));
                    book.setAuthor(a);

                    Stock s = new Stock();
                    s.setIdStock(rs.getInt("id_stock"));
                    s.setLastAddedDate(rs.getTimestamp("last_added_date").toLocalDateTime());
                    s.setPrice(rs.getDouble("price"));
                    s.setQuantity(rs.getInt("quantity"));
                    book.setStock(s);

                    list.add(book);
                    System.out.println(book);
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
