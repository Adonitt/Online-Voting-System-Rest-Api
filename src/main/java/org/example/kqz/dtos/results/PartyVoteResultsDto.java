package org.example.kqz.dtos.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyVoteResultsDto {
    private Long partyId;
    private String name;
    private Long totalVotes;
    double percentage;
}
