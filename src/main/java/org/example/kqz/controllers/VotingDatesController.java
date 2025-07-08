package org.example.kqz.controllers;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.votes.VotingDates;
import org.example.kqz.services.interfaces.VotingDatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/voting-dates")
@RequiredArgsConstructor
public class VotingDatesController {

    private final VotingDatesService votingDatesService;

    @GetMapping
    public ResponseEntity<List<VotingDates>> getAll() {
        return ResponseEntity.ok(votingDatesService.getAllVotingDates());
    }

    @PostMapping("/create")
    public ResponseEntity<VotingDates> createVotingDate(@RequestBody VotingDates votingDates) {
        VotingDates created = votingDatesService.createVotingDates(votingDates);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VotingDates> update(@PathVariable Long id, @RequestBody VotingDates votingDates) {
        return ResponseEntity.ok(votingDatesService.updateVotingDates(id, votingDates));
    }


    @GetMapping("/default")
    public VotingDates defaultObject() {
        return new VotingDates();
    }

}
