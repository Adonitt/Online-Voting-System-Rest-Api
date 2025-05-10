package org.example.kqz.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails authenticate(String email, String password);

    String generateToken(UserDetails userDetails);

    UserDetails validateToken(String token);
}
