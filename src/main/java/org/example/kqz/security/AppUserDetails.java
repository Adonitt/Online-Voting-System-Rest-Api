package org.example.kqz.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor

public class AppUserDetails implements UserDetails {

    private final UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public Long getId() {
        return user.getId();
    }

    public UserEntity getUser() {
        return user;
    }

}
