package org.example.kqz.controllers;

import org.example.kqz.entities.enums.NationalityEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/nationalities")
    public ResponseEntity<List<String>> getNationalities() {
        List<String> values = Arrays.stream(NationalityEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(values);
    }
}
