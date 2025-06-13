package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.results.CandidateVoteResultDto;
import org.example.kqz.dtos.results.CityVoteSummaryDto;
import org.example.kqz.dtos.results.PartyVoteResultsDto;
import org.example.kqz.dtos.results.UserVoteDto;

import java.util.List;

public interface VoteResultsService {
    List<PartyVoteResultsDto> getPartyResults();

    List<CandidateVoteResultDto> getCandidateResults();

    List<UserVoteDto> getUserVotesById(Long userId);

    List<UserVoteDto> getAllUserVotes();

    PartyVoteResultsDto getPartyResultById(Long partyId);

    List<CityVoteSummaryDto> getCityPartyVoteSummary();

}
