package org.example.kqz.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.dtos.candidates.UpdateCandidateRequestDto;
import org.example.kqz.services.interfaces.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService service;

    @GetMapping("")
    public ResponseEntity<List<CRDCandidateRequestDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CRDCandidateRequestDto> findById(@PathVariable Long id) {
        CRDCandidateRequestDto candidate = service.findById(id);
        return ResponseEntity.ok(candidate);
    }

    @PostMapping("/create")
    public ResponseEntity<CRDCandidateRequestDto> create(
            @RequestPart("dto") @Valid CRDCandidateRequestDto dto,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {

        CRDCandidateRequestDto saved = service.add(dto, photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCandidateRequestDto> modify(
            @RequestPart("dto") @Valid UpdateCandidateRequestDto dto,
            @RequestPart(value = "photo", required = false) MultipartFile photo,
            @PathVariable Long id) {

        UpdateCandidateRequestDto updated = service.modify(dto, id, photo);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/default")
    public CRDCandidateRequestDto defaultDriver() {
        return new CRDCandidateRequestDto();
    }

}
