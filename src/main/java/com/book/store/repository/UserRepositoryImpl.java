package com.book.store.repository;

import com.book.store.model.Role;
import com.book.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SET_USER_SQL = "INSERT INTO user(name, surname, username, email, password, id_role, token, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTIVATE_USER_BY_TOKEN_SQL = "UPDATE user SET token=?, status=? WHERE token=?";
    private static final String GET_USER_BY_USERNAME_SQL = "SELECT * FROM user u INNER JOIN role r on u.id_role = r.id_role WHERE u.username = ?";

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
        User user1 = null;
        try {
            user1 = jdbcTemplate.queryForObject(GET_USER_BY_USERNAME_SQL, new Object[]{username}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int i) throws SQLException {
                    User user = new User();
                    user.setIdUser(rs.getInt("id_user"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setStatus(rs.getInt("status"));

                    Role role = new Role();
                    role.setIdRole(rs.getInt("id_role"));
                    role.setRoleType(rs.getString("role_type"));
                    user.setRole(role);

                    return user;
                }
            });
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return user1;
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
