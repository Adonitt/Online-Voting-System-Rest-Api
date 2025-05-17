package org.example.kqz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.example.kqz.dtos.auth.AuthResponse;
import org.example.kqz.dtos.auth.LoginRequest;
import org.example.kqz.dtos.user.ChangePasswordDto;
import org.example.kqz.services.interfaces.AuthService;
import org.example.kqz.services.interfaces.ChangePasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    private final ChangePasswordService changePasswordService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // hapi 1 - Authenticate user
        var user = service.authenticate(request.getEmail(), request.getPassword());

        // hapi 2 - Generate token
        var token = service.generateToken(user);

        var authResponse = new AuthResponse(token, 86400000L); // 24 h in milliseconds

        return ResponseEntity.ok(authResponse);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto request, Authentication authentication) {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        changePasswordService.changePassword(request, email);
        return ResponseEntity.ok("Password changed successfully.");
    }

    @GetMapping("/change-password/default")
    public ChangePasswordDto defaultObject() {
        return new ChangePasswordDto();
    }

}
