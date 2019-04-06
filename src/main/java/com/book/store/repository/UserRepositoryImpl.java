package com.book.store.repository;

import com.book.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    //fields
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String ADD_USER_SQL = "INSERT INTO user(name, surname, username, email, password, id_role, token, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTIVATE_USER_BY_TOKEN_SQL = "UPDATE user SET token=?, status=? WHERE token=?";
    private static final String GET_USER_BY_USERNAME_SQL = "select * from user u inner join role r on u.id_role = r.id_role left join wishlist w on u.id_user = w.id_user where u.username = ?";
    private static final String UPDATE_USER_SQL = "update user set name = ?, surname = ?, username = ?, password = ? where email = ?";
    private static final String ADD_BILLING_INFO = "insert into billing_info(firstname, lastname, company_name, country, address, postcode, phone, email, id_user) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SUCSCRIBE_SQL = "insert into subscriber(email) values(?)";

    //methods
    @Override
    public void register(User user, BillingInfo billingInfo) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement(ADD_USER_SQL, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getSurname());
                    statement.setString(3, user.getUsername());
                    statement.setString(4, user.getEmail());
                    statement.setString(5, user.getPassword());
                    statement.setInt(6, user.getRole().getIdRole());
                    statement.setString(7, user.getToken());
                    statement.setInt(8, user.getStatus());
                    return statement;
                }
            }, holder);

            long idUser = holder.getKey().longValue();
            jdbcTemplate.update(ADD_BILLING_INFO, billingInfo.getFirstname(), billingInfo.getLastname(), billingInfo.getCompanyName(), billingInfo.getCountry(), billingInfo.getAddress(), billingInfo.getPostcode(), billingInfo.getPhone(), billingInfo.getEmail(), idUser);

        } catch (DuplicateKeyException e){
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> list = jdbcTemplate.query(GET_USER_BY_USERNAME_SQL, new Object[]{username}, new ResultSetExtractor<List<User>>() {
            @Override
            public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Integer, User> map = new HashMap<>();

                while (rs.next()) {
                    User user = map.get(rs.getInt("u.id_user"));

                    if (user == null) {
                        user = new User();
                        user.setIdUser(rs.getInt("u.id_user"));
                        user.setName(rs.getString("u.name"));
                        user.setSurname(rs.getString("u.surname"));
                        user.setUsername(rs.getString("u.username"));
                        user.setEmail(rs.getString("u.email"));
                        user.setPassword(rs.getString("u.password"));
                        user.setStatus(rs.getInt("u.status"));

                        Role role = new Role();
                        role.setIdRole(rs.getInt("r.id_role"));
                        role.setRoleType(rs.getString("r.role_type"));
                        user.setRole(role);

                        map.put(user.getIdUser(), user);
                    }

                    user.addWishlist(rs.getInt("w.id_book"));

                }
                return new ArrayList<>(map.values());
            }
        });
        return list.get(0);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_SQL, user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public void sucscribe(String email) {
        int affectedRow = jdbcTemplate.update(SUCSCRIBE_SQL, email);
        if (affectedRow == 0) {
            throw new RuntimeException();
        }
    }

    @Override
    public void activateUserByToken(String token) {
        String newToken = UUID.randomUUID().toString();
        int status = 1;

        int affectedRow = jdbcTemplate.update(ACTIVATE_USER_BY_TOKEN_SQL, newToken, status, token);
        if (affectedRow == 0){
            try {
                throw new IllegalAccessException("Token is Invalide");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
