package com.book.store.model;

import com.book.store.util.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements UserDetails {

    private int idUser;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String token;
    private int status;
    private List<Integer> idWishlists;
    private Role role;

    public User() {
        this.idWishlists = new ArrayList<>();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleType());
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(authority);
        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != Constants.USER_STATUS_BLOCK;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status != Constants.USER_STATUS_INACTIVE;
    }

    public void addWishlist(Integer idBook) {
        this.idWishlists.add(idBook);
    }

    public void removeWishlist(Integer idBook) {
        this.idWishlists.remove(new Integer(idBook));
    }

}
