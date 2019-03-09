package com.book.store.repository;

import com.book.store.model.Book;
import com.book.store.model.Role;
import com.book.store.model.User;
import com.book.store.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SET_USER_SQL = "INSERT INTO user(name, surname, username, email, password, id_role, token, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTIVATE_USER_BY_TOKEN_SQL = "UPDATE user SET token=?, status=? WHERE token=?";
    private static final String GET_USER_BY_USERNAME_SQL = "select * from user u inner join role r on u.id_role = r.id_role left join wishlist w on u.id_user = w.id_user where u.username = ?";

    @Override
    public void register(User user) {
        try {
            jdbcTemplate.update(SET_USER_SQL, user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole().getIdRole(), user.getToken(), user.getStatus());
        }catch (DuplicateKeyException e){
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

                    user.addWishlist(rs.getInt("w.id_wishlist"));

                }
                return new ArrayList<>(map.values());
            }
        });
        return list.get(0);
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
