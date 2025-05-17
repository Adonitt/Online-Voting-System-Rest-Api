package org.example.kqz.configs;

import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.enums.RoleEnum;
import org.example.kqz.repositories.UserRepository;
import org.example.kqz.security.AppUserDetailsService;
import org.example.kqz.security.JwtAuthenticationFilter;
import org.example.kqz.services.interfaces.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthService authService) {
        return new JwtAuthenticationFilter(authService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        // per requests qe nuk duhet auth vendosen te request matchers
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/default").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rks/suffrages").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/parties").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/parties/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/parties/add").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/parties/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/candidates").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/candidates/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/candidates/create").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/candidates/{id}").permitAll()

                        .anyRequest().authenticated()
                ).csrf(csrf -> csrf.disable()) // cross site request forgery
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        var user = new AppUserDetailsService(repository);

//        String email = "user@test.com";
//
//        repository.findByEmail(email).orElseGet(() -> {
//            var newUser = UserEntity.builder()
//                    .firstName("Adonit")
//                    .lastName("Halili")
//                    .personalNo("1252334056")
//                    .birthDate(LocalDate.of(2005, 2, 12))
//                    .registeredAt(LocalDateTime.now())
//                    .hasVoted(false)
//                    .email(email)
//
//                    .password(passwordEncoder().encode("password"))
//                    .role(RoleEnum.ADMIN)
//                    .build();
//
//            return repository.save(newUser);
//        });
        return user;
    }


}
