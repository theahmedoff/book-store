package com.book.store.repository;

import com.book.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String SET_USER_SQL = "INSERT INTO user(name, surname, username, email, password, id_role, token, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void register(User user) {
        jdbcTemplate.update(SET_USER_SQL, user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole().getRoleType(), user.getToken(), user.getStatus());
    }
}
