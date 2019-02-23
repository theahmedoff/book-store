package com.book.store.repository;

import com.book.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {

    //fields
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_CATEGORIES_SQL = "select * from category";
    private static final String GET_BOOKS_BY_MULTIPLE_PARAMETER_SQL = "select * from (select b.id_book, b.title, b.desc, b.image_path, b.language, b.write_date, avg(r.rating) as average_rating, b.id_author from book b left join review r on b.id_book=r.id_book group by b.id_book) book inner join author a on book.id_author=a.id_author inner join stock s on book.id_book=s.id_book";
    private static final String GET_ALL_LAST_BOOK_SQL = "select * from (select b.id_book, b.title, b.desc, b.image_path, b.language, b.write_date, avg(r.rating) as average_rating, b.id_author from book b left join review r on b.id_book=r.id_book group by b.id_book) book inner join author a on book.id_author=a.id_author inner join stock s on book.id_book=s.id_book order by last_added_date desc limit 6";
    private static final String GET_BOOK_BY_ID_SQL = "select * from (select b.id_book, b.title, b.desc, b.image_path, b.language, b.write_date, avg(r.rating) as average_rating, b.id_author from book b left join review r on b.id_book=r.id_book group by b.id_book) book inner join author a on book.id_author=a.id_author inner join stock s on book.id_book=s.id_book inner join book_category bc on book.id_book=bc.id_book inner join category c on bc.id_category=c.id_category left join review re on book.id_book = re.id_book inner join user u on re.id_user = u.id_user where book.id_book = ?";

    //methods
    @Override
    public List<Book> getBooksByMultipleParameters(SearchEntity searchEntity) {
        StringBuilder builder = new StringBuilder(GET_BOOKS_BY_MULTIPLE_PARAMETER_SQL);
        int categoriesLength = (searchEntity.getCategories() == null) ? 0 : searchEntity.getCategories().length;
        Object[] parameters;
        int counter = 0;

        if (categoriesLength != 0) {
            builder.append(" inner join book_category bc on book.id_book=bc.id_book inner join category c on bc.id_category=c.id_category where (s.age_range > ? and s.age_range < ?) and (s.price > ? and s.price < ?) and (");
            for (int i = 0; i < searchEntity.getCategories().length; i++) {
                builder.append("c.id_category = ?");
                counter++;
                if (counter != searchEntity.getCategories().length) {
                    builder.append(" or ");
                }
            }
            builder.append(")");
            parameters = new Object[]{searchEntity.getMinAge(), searchEntity.getMaxAge(), searchEntity.getMinPrice(), searchEntity.getMaxPrice()};
            parameters = add(parameters, searchEntity.getCategories());

        } else {
            builder.append(" where (s.age_range > ? and s.age_range < ?) and (s.price > ? and s.price < ?)");
            parameters = new Object[]{searchEntity.getMinAge(), searchEntity.getMaxAge(), searchEntity.getMinPrice(), searchEntity.getMaxPrice()};
        }


        List<Book> books = jdbcTemplate.query(builder.toString(), parameters, new ResultSetExtractor<List<Book>>() {

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
                    stock.setAgeRange(rs.getInt("age_range"));
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
                List<Book> list = new LinkedList<>();
                while (rs.next()){
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
                    stock.setAgeRange(rs.getInt("age_range"));
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
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(GET_ALL_CATEGORIES_SQL, new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public Book getBookById(int idBook) {
        List<Book> list = jdbcTemplate.query(GET_BOOK_BY_ID_SQL, new Object[]{idBook}, new ResultSetExtractor<List<Book>>(){

            @Override
            public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Integer, Book> bookMap = new HashMap<>();
                Map<Integer, Category> categoryMap = new HashMap<>();
                Map<Integer, Review> reviewMap = new HashMap<>();
                List<Book> list = new ArrayList<>();

                while (rs.next()) {
                    Book book = bookMap.get(rs.getInt("book.id_book"));

                    if (book == null) {
                        book = new Book();
                        book.setIdBook(rs.getInt("book.id_book"));
                        book.setTitle(rs.getString("book.title"));
                        book.setDesc(rs.getString("book.desc"));
                        book.setImagePath(rs.getString("book.image_path"));
                        book.setLanguage(rs.getString("book.language"));
                        book.setWriteDate(rs.getDate("book.write_date").toLocalDate());
                        book.setAvarageRating(rs.getDouble("book.average_rating"));

                        Author author = new Author();
                        author.setIdAuthor(rs.getInt("a.id_author"));
                        author.setFullName(rs.getString("a.full_name"));
                        book.setAuthor(author);

                        Stock stock = new Stock();
                        stock.setIdStock(rs.getInt("s.id_stock"));
                        stock.setQuantity(rs.getInt("s.quantity"));
                        stock.setPrice(rs.getDouble("s.price"));
                        stock.setAgeRange(rs.getInt("s.age_range"));
                        stock.setLastAddedDate(rs.getTimestamp("s.last_added_date").toLocalDateTime());
                        book.setStock(stock);

                        bookMap.put(book.getIdBook(), book);
                    }

                    if (categoryMap.get(rs.getInt("c.id_category")) == null) {
                        Category category = new Category();
                        category.setIdCategory(rs.getInt("c.id_category"));
                        category.setType(rs.getString("c.type"));
                        book.addCategory(category);
                        categoryMap.put(category.getIdCategory(), category);
                    }

                    if (reviewMap.get(rs.getInt("re.id_review")) == null) {
                        Review review = new Review();
                        review.setIdReview(rs.getInt("re.id_review"));
                        review.setDesc(rs.getString("re.desc"));
                        review.setWriteDate(rs.getTimestamp("re.write_date").toLocalDateTime());
                        review.setRating(rs.getInt("re.rating"));

                        User user = new User();
                        user.setIdUser(rs.getInt("u.id_user"));
                        user.setName(rs.getString("u.name"));
                        user.setSurname(rs.getString("u.surname"));
                        user.setUsername(rs.getString("u.username"));
                        user.setEmail(rs.getString("u.email"));

                        review.setUser(user);
                        book.addReview(review);
                        reviewMap.put(review.getIdReview(), review);
                    }

                    list.add(book);
                }

                return list;
            }
        });

        System.out.println(list.size());
        return list.get(0);
    }
















    //private methods
    private Object[] add(Object[] arr, Object... elements){
        Object[] tempArr = new Object[arr.length+elements.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);

        for(int i=0; i < elements.length; i++)
            tempArr[arr.length+i] = elements[i];
        return tempArr;

    }

}
