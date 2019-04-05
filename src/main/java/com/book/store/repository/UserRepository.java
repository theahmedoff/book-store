package com.book.store.repository;

import com.book.store.model.BillingInfo;
import com.book.store.model.User;

public interface UserRepository {

    void register(User user, BillingInfo billingInfo);

    void activateUserByToken(String token);

    User getUserByUsername(String username);

    void updateUser(User user);

    void sucscribe(String email);

}
