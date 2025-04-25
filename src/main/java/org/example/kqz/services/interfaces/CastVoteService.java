package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.votes.VoteRequestDto;

public interface CastVoteService {
    void castVote(VoteRequestDto voteRequestDto);

}