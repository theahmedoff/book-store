package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int idUser;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String token;
    private int status;
    private Role role;

}
