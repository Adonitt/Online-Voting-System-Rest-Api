package org.example.kqz.services.impls;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.example.kqz.exceptions.ResourceNotFoundException;
import org.example.kqz.services.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    // qe me marre ni value prej application.properties - qysh e kem shenu atje, duhet edhe ktu
    @Value("${jwt.secret}")
    private String secretKey;

    private final Long expirationTime = 86400000L;

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Objects> claims = new HashMap<>();

        // token builder pattern
        return Jwts.builder()
                .setClaims(claims) // ckado qe vendoset ne body te tokenit, quhen claims
                .setSubject(userDetails.getUsername()) // email ne rastin tone
                .setIssuedAt(new Date(System.currentTimeMillis())) // kur ka fillu
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // kur ka mu ba expire tokeni
                .signWith(getSecretKey(), SignatureAlgorithm.HS256) // secret key me rujte me ni algoritem
                .compact();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Override
    public UserDetails validateToken(String token) {
        String email = extractUsername(token);
        return userDetailsService.loadUserByUsername(email);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
        // ne body qe na u kthy prej tokenit, po i thojme qe me marre veq sub, qe ne rastin tone osht email
    }

    // Metoda për të marrë Authentication
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // Metoda për të marrë email-in e përdoruesit të loguar
    public static String getLoggedInUserEmail() {
        Authentication authentication = getAuthentication();
        return authentication.getName();  // Merr emailin ose username-in
    }

    // Metoda për të marrë rolin e përdoruesit të loguar
    public static String getLoggedInUserRole() {
        Authentication authentication = getAuthentication();
        return authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

}
