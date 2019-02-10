package com.book.store.service;

import com.book.store.model.User;
import com.book.store.repository.UserRepository;

public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    @Override
    public void register(User user) {
        userRepository.register(user);
    }
}
