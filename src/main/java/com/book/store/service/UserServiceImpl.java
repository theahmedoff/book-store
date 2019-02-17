package com.book.store.service;

import com.book.store.model.User;
import com.book.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    @Override
    public void register(User user) {
        userRepository.register(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(s);

        if (user == null){
            throw new UsernameNotFoundException(s);
        }

        return user;
    }

    @Override
    public void upadteUserByStatusByToken(String token) {
        userRepository.upadteUserByStatusByToken(token);
    }


}
