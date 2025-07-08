package org.example.kqz.controllers;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.votes.VotingDates;
import org.example.kqz.services.interfaces.VotingDatesService;
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


    @PutMapping("/{id}")
    public ResponseEntity<VotingDates> update(@PathVariable Long id, @RequestBody VotingDates votingDates) {
        return ResponseEntity.ok(votingDatesService.updateVotingDates(id, votingDates));
    }

}
