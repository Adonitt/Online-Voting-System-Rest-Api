package org.example.kqz.configs;

import org.example.kqz.entities.UserEntity;
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
                        // Allow public login endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/forgot-password").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/enums/**").permitAll()

                        // Allow authenticated users to change password
                        .requestMatchers(HttpMethod.PUT, "/api/v1/auth/change-password").authenticated()

                        // Allow USER and ADMIN roles to get their own profile or user info
                        // (adjust if you want to allow e.g. /api/v1/users/{id} for USER only if they match the id)
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/profile").hasAnyRole(RoleEnum.USER.name(), RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole(RoleEnum.USER.name(), RoleEnum.ADMIN.name())

                        // Voting endpoints accessible to USER role
                        .requestMatchers("/api/v1/votes/**").hasRole(RoleEnum.USER.name())
                        .requestMatchers("/api/votes/results/**").hasAnyRole(RoleEnum.USER.name(), RoleEnum.ADMIN.name())

                        .requestMatchers(HttpMethod.GET, "api/v1/parties/{id}").permitAll()

                        // All other /api/v1/** endpoints restricted to ADMIN only
                        .requestMatchers(HttpMethod.GET, "/api/v1/**").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole(RoleEnum.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole(RoleEnum.ADMIN.name())


                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        var user = new AppUserDetailsService(repository);

        String email = "adonit@halili.com";

        repository.findByEmail(email).orElseGet(() -> {
            var newUser = UserEntity.builder()
                    .firstName("Adonit")
                    .lastName("Halili")
                    .personalNo("1252334056")
                    .nationality(NationalityEnum.ALBANIAN)
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
