package com.book.store.repository;

import com.book.store.model.User;

public interface UserRepository {

    void register(User user);

    User getUserByEmail(String email);

    void activateUserByToken(String token);
}
