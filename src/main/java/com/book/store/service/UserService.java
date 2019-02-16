package com.book.store.service;

import com.book.store.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    void register(User user);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    void upadteUserByStatusByToken(String token);
}
