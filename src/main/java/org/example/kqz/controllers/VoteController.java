package org.example.kqz.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.votes.VoteRequestDto;
import org.example.kqz.dtos.votes.VoteResponseDto;
import org.example.kqz.dtos.votes.VotingDates;
import org.example.kqz.services.interfaces.CastVoteService;
import org.example.kqz.services.interfaces.VotingDatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votes")
@RequiredArgsConstructor
public class VoteController {

    private final CastVoteService service;
    private final VotingDatesService votingDatesService;

    @PostMapping("/vote")
    public ResponseEntity<VoteResponseDto> castVote(@Valid @RequestBody VoteRequestDto voteRequestDto) {
        VoteResponseDto response = service.castVote(voteRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/default")
    public VoteRequestDto defaultObject() {
        return new VoteRequestDto();
    }

    @GetMapping("/voting-days")
    public ResponseEntity<VotingDates> getVotingDays() {
        VotingDates currentDates = votingDatesService.getCurrentVotingDates();
        return ResponseEntity.ok(currentDates);
    }
}

