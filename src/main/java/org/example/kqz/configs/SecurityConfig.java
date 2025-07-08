package org.example.kqz.configs;

import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.enums.CityEnum;
import org.example.kqz.entities.enums.NationalityEnum;
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
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsConfig corsConfig;

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

        http.
                cors().and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rks/suffrages").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/forgot-password").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/enums/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/voting-dates").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/api/v1/auth/change-password").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/v1/users/profile").hasAnyRole(RoleEnum.USER.name(), RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole(RoleEnum.USER.name(), RoleEnum.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "/api/v1/votes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/votes/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/parties/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/parties").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/parties/{id}").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/parties").hasRole(RoleEnum.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "/api/v1/candidates").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/candidates/{id}").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/candidates/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole(RoleEnum.ADMIN.name())

                        .anyRequest().authenticated()
                )

                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        var user = new AppUserDetailsService(repository);

        String email = "admin@rks.com";

        repository.findByEmail(email).orElseGet(() -> {
            var newUser = UserEntity.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .personalNo("1252334056")
                    .nationality(NationalityEnum.ALBANIAN)
                    .city(CityEnum.PODUJEVO)
                    .birthDate(LocalDate.of(2005, 2, 12))
                    .registeredAt(LocalDateTime.now())
                    .hasVoted(false)
                    .email(email)

                    .password(passwordEncoder().encode("Password1."))
                    .role(RoleEnum.ADMIN)
                    .build();

            return repository.save(newUser);
        });
        return user;
    }


}
