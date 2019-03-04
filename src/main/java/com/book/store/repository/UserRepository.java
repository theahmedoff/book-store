package com.book.store.repository;

import com.book.store.model.User;

public interface UserRepository {

    void register(User user);

    void activateUserByToken(String token);

    User getUserByUsername(String username);
}
