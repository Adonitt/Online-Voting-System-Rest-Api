package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.results.CandidateVoteResultDto;
import org.example.kqz.dtos.results.CityVoteSummaryDto;
import org.example.kqz.dtos.results.PartyVoteResultsDto;
import org.example.kqz.dtos.results.UserVoteDto;
import org.example.kqz.entities.enums.CityEnum;

import java.util.List;

public interface VoteResultsService {
    List<PartyVoteResultsDto> getPartyResults();

    List<CandidateVoteResultDto> getCandidateResults();

    List<UserVoteDto> getUserVotes(Long userId);

    PartyVoteResultsDto getPartyResultById(Long partyId);

    public List<CityVoteSummaryDto> getCityPartyVoteSummary();
}
