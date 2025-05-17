package org.example.kqz.controllers;

import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.CitizensEntity;
import org.example.kqz.services.citizens.CitizensService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rks/suffrages")
public class CitizensController {

    private final CitizensService service;

    @GetMapping("")
    public ResponseEntity<List<CitizensEntity>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
