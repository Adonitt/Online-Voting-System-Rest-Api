package org.example.kqz.controllers;

import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.SuffrageEntity;
import org.example.kqz.services.suffrage.SuffrageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rks/suffrages")
public class SuffrageController {

    private final SuffrageService service;

    @GetMapping("")
    public ResponseEntity<List<SuffrageEntity>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
