package org.example.kqz.security;

import lombok.RequiredArgsConstructor;
import org.example.kqz.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor

public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = repository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
        return new AppUserDetails(user);
    }
}
