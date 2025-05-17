package org.example.kqz.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.votes.VoteRequestDto;
import org.example.kqz.dtos.votes.VoteResponseDto;
import org.example.kqz.services.interfaces.CastVoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votes")
@RequiredArgsConstructor
public class VoteController {
    private final CastVoteService service;

    @PostMapping("/vote")
    public ResponseEntity<VoteResponseDto> vote(@RequestBody @Valid VoteRequestDto voteRequestDto) {
        VoteResponseDto response = service.castVote(voteRequestDto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/default")
    public VoteResponseDto defaultObject() {
        return new VoteResponseDto();
    }
}
