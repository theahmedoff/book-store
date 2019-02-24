package com.book.store.repository;

import com.book.store.model.Book;
import com.book.store.model.Cart;
import com.book.store.model.Stock;
import com.book.store.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private JdbcTemplate jdbcTemplate;

    public static final String GET_ALL_CART_LIST_SQL = "select b.id_book, b.image_path, b.title, s.id_stock, s.price, s.quantity as stockQuantity, u.id_user, u.email, c.id_cart, c.quantity as cartQuantity FROM book b INNER JOIN stock s on b.id_book = s.id_book INNER JOIN user u on u.id_user = id_user INNER JOIN cart c on c.id_book = b.id_book WHERE u.email = ?";

    @Override
    public List<Cart> getAllCartListByUserId(String email) {
        List<Cart> carts = jdbcTemplate.query(GET_ALL_CART_LIST_SQL, new Object[]{email}, new ResultSetExtractor<List<Cart>>() {
            @Override
            public List<Cart> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Cart> cartList = new ArrayList<>();

                while (rs.next()){
                    Book book = new Book();
                    book.setIdBook(rs.getInt("id_book"));
                    book.setImagePath(rs.getString("image_path"));
                    book.setTitle(rs.getString("title"));

                    Stock stock = new Stock();
                    stock.setIdStock(rs.getInt("id_stock"));
                    stock.setPrice(rs.getInt("price"));
                    stock.setQuantity(rs.getInt("stockQuantity"));
                    book.setStock(stock);

                    User user = new User();
                    user.setIdUser(rs.getInt("id_user"));
                    user.setEmail(rs.getString("email"));

                    Cart cart = new Cart();
                    cart.setBook(book);
                    cart.setUser(user);
                    cart.setQuantity(rs.getInt("cartQuantity"));

                    cartList.add(cart);
                }

                return cartList;

            }
        });
        return carts;
    }
}
