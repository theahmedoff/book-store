package com.book.store.repository;

import com.book.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {

    //fields
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String GET_CARTS_BY_ID_USER_SQL = "select b.id_book, b.first_image_path, b.second_image_path, b.title, s.id_stock, s.price, s.discount, s.quantity as stockQuantity, u.id_user, u.email, c.id_cart, c.quantity as cartQuantity FROM book b INNER JOIN stock s on b.id_book = s.id_book INNER JOIN user u on u.id_user = id_user INNER JOIN cart c on c.id_book = b.id_book WHERE u.id_user = ?";
    private static final String DELETE_CART_SQL = "delete from cart where id_cart = ?";
    private static final String GET_WISHLISTS_BY_ID_USER_SQL = "select w.id_wishlist, u.id_user, u.name, u.surname, u.username, u.email, b.id_book, b.title, b.first_image_path, b.second_image_path, s.id_stock, s.quantity, s.price, s.discount from wishlist w inner join user u on w.id_user = u.id_user inner join book b on w.id_book = b.id_book inner join stock s on s.id_book = b.id_book where u.id_user = ?";
    private static final String DELETE_WISHLIST_SQL = "delete from wishlist where id_book = ? and id_user = ?";
    private static final String ADD_TO_CART_SQL = "insert into cart(id_user, id_book, quantity) values(?, ?, ?);";
    private static final String ADD_TO_WISHLIST_SQL = "insert into wishlist(id_user, id_book) values(?, ?)";
    private static final String UPDATE_CART_SQL = "update cart set quantity = ? where id_cart = ?";
    private static final String UPDATE_QUANTITY_OF_CART_SQL = "update cart set quantity = quantity + ? where id_user = ? and id_book = ?";
    private static final String GET_BILLING_INFO = "select id_billing_info, firstname, lastname, company_name, country, address, postcode, phone, email from billing_info where id_user = ?";
    private static final String UPDATE_BILLING_INFO = "update billing_info set firstname = ?, lastname = ?, company_name = ?, country = ?, address = ?, postcode = ?, phone = ?, email = ? where id_user = ?";


    //methods
    @Override
    public List<Cart> getCartsById(int idUser) {
        List<Cart> carts = jdbcTemplate.query(GET_CARTS_BY_ID_USER_SQL, new Object[]{idUser}, new ResultSetExtractor<List<Cart>>() {
            @Override
            public List<Cart> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Cart> cartList = new ArrayList<>();

                while (rs.next()) {
                    Book book = new Book();
                    book.setIdBook(rs.getInt("id_book"));
                    book.setFirstImagePath(rs.getString("first_image_path"));
                    book.setSecondImagePath(rs.getString("second_image_path"));
                    book.setTitle(rs.getString("title"));

                    Stock stock = new Stock();
                    stock.setIdStock(rs.getInt("id_stock"));
                    stock.setPrice(rs.getDouble("price"));
                    stock.setDiscount(rs.getInt("discount"));
                    stock.setQuantity(rs.getInt("stockQuantity"));
                    book.setStock(stock);

                    User user = new User();
                    user.setIdUser(rs.getInt("id_user"));
                    user.setEmail(rs.getString("email"));

                    Cart cart = new Cart();
                    cart.setIdCart(rs.getInt("id_cart"));
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

    @Override
    public void deleteCartById(int id) {
        int affectedRows = jdbcTemplate.update(DELETE_CART_SQL, id);
    }


    @Override
    public List<Wishlist> getWishlistsByIdUser(int idUser) {
        List<Wishlist> wishlists = jdbcTemplate.query(GET_WISHLISTS_BY_ID_USER_SQL, new Object[]{idUser}, new ResultSetExtractor<List<Wishlist>>() {
            @Override
            public List<Wishlist> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Wishlist> list = new ArrayList<>();
                while (rs.next()) {
                    Book book = new Book();
                    book.setIdBook(rs.getInt("id_book"));
                    book.setTitle(rs.getString("title"));
                    book.setFirstImagePath(rs.getString("first_image_path"));
                    book.setSecondImagePath(rs.getString("second_image_path"));

                    Stock stock = new Stock();
                    stock.setIdStock(rs.getInt("id_stock"));
                    stock.setQuantity(rs.getInt("quantity"));
                    stock.setPrice(rs.getDouble("price"));
                    stock.setDiscount(rs.getInt("discount"));
                    book.setStock(stock);

                    User user = new User();
                    user.setIdUser(rs.getInt("id_user"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));

                    Wishlist wishlist = new Wishlist();
                    wishlist.setIdWishlist(rs.getInt("id_wishlist"));
                    wishlist.setBook(book);
                    wishlist.setUser(user);

                    list.add(wishlist);
                }

                return list;
            }
        });
        return wishlists;
    }

    @Override
    public void deleteWishlist(int idBook, int idUser) {
        int affectedRows = jdbcTemplate.update(DELETE_WISHLIST_SQL, idBook, idUser);
        if (affectedRows == 0) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addToCart(int idUser, int idBook, Integer idWishlist, Integer quantity) {
        try {
            jdbcTemplate.update(ADD_TO_CART_SQL, idUser, idBook, quantity);

        } catch (DataAccessException e) {
            jdbcTemplate.update(UPDATE_QUANTITY_OF_CART_SQL, quantity, idUser, idBook);
        }

        if (idWishlist != null) {
            deleteWishlist(idBook, idUser);
        }
    }

    @Override
    public void addToWishlist(int idUser, int idBook) {
        int affectedRows = jdbcTemplate.update(ADD_TO_WISHLIST_SQL, idUser, idBook);
        if (affectedRows == 0) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateCart(int idCart, int quantity) {
        int affectedRows = jdbcTemplate.update(UPDATE_CART_SQL, quantity, idCart);
        if (affectedRows == 0) {
            throw new RuntimeException();
        }
    }

    @Override
    public BillingInfo getBillingInfo(int idUser) {
        BillingInfo billingInfo;
        try {
            billingInfo = jdbcTemplate.queryForObject(GET_BILLING_INFO, new Object[]{idUser}, new RowMapper<BillingInfo>() {
                @Override
                public BillingInfo mapRow(ResultSet rs, int i) throws SQLException {
                    BillingInfo info = new BillingInfo();
                    info.setIdBillingInfo(rs.getInt("id_billing_info"));
                    info.setFirstname(rs.getString("firstname"));
                    info.setLastname(rs.getString("lastname"));
                    info.setCompanyName(rs.getString("company_name"));
                    info.setCountry(rs.getString("country"));
                    info.setAddress(rs.getString("address"));
                    info.setPostcode(rs.getString("postcode"));
                    info.setPhone(rs.getString("phone"));
                    info.setEmail(rs.getString("email"));

                    return info;
                }
            });

        } catch (EmptyResultDataAccessException e) {
            billingInfo = new BillingInfo();
        }

        return billingInfo;
    }

    @Override
    public BillingInfo updateBillingInfo(int idUser, BillingInfo billingInfo) {
        jdbcTemplate.update(UPDATE_BILLING_INFO, billingInfo.getFirstname(), billingInfo.getLastname(), billingInfo.getCompanyName(), billingInfo.getCountry(), billingInfo.getAddress(), billingInfo.getPostcode(), billingInfo.getPhone(), billingInfo.getEmail(), idUser);
        return getBillingInfo(idUser);
    }
}
