package org.example.kqz.controllers;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.results.CandidateVoteResultDto;
import org.example.kqz.dtos.results.CityVoteSummaryDto;
import org.example.kqz.dtos.results.PartyVoteResultsDto;
import org.example.kqz.dtos.results.UserVoteDto;
import org.example.kqz.services.interfaces.VoteResultsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/votes/results")
@RequiredArgsConstructor
public class VoteResultsController {

    private final VoteResultsService voteResultService;

    @GetMapping("/party")
    public List<PartyVoteResultsDto> getPartyResults() {
        return voteResultService.getPartyResults();
    }

    @GetMapping("/party/{id}")
    public PartyVoteResultsDto getPartyResultById(@PathVariable Long id) {
        return voteResultService.getPartyResultById(id);
    }

    @GetMapping("/candidate")
    public List<CandidateVoteResultDto> getCandidateResults() {
        return voteResultService.getCandidateResults();
    }

    @GetMapping("/user/{userId}")
    public List<UserVoteDto> getUserVotes(@PathVariable Long userId) {
        return voteResultService.getUserVotesById(userId);
    }

    @GetMapping("/all-users")
    public List<UserVoteDto> getAllUserVotes() {
        return voteResultService.getAllUserVotes();
    }

    @GetMapping("/city-summary")
    public List<CityVoteSummaryDto> getVoteSummaryByCity() {
        return voteResultService.getCityPartyVoteSummary();
    }


}
