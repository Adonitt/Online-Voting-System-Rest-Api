package org.example.kqz.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.user.CreateUserRequestDto;
import org.example.kqz.dtos.user.UpdateUserRequestDto;
import org.example.kqz.dtos.user.UserDetailsDto;
import org.example.kqz.dtos.user.UserListingDto;
import org.example.kqz.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;

    @GetMapping("")
    public ResponseEntity<List<UserListingDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> findById(@PathVariable Long id) {
        UserDetailsDto userDetails = service.findById(id);
        return ResponseEntity.ok(userDetails);
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserRequestDto> register(@Valid @RequestBody CreateUserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserRequestDto> modify(@Valid @RequestBody UpdateUserRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.modify(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/default")
    public CreateUserRequestDto defaultDriver() {
        return new CreateUserRequestDto();
    }



}
