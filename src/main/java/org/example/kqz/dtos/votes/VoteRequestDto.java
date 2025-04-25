package org.example.kqz.dtos.votes;

import java.util.List;

public class VoteRequestDto {
    private Long userId;
    private Long partyId;
    private List<Long> candidateIds;
}
