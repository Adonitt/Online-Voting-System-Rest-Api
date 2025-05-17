package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.votes.VoteRequestDto;
import org.example.kqz.dtos.votes.VoteResponseDto;

public interface CastVoteService {
    VoteResponseDto castVote(VoteRequestDto requestDto);
}