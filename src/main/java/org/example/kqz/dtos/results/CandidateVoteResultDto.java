package org.example.kqz.dtos.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateVoteResultDto {
    private Long candidateId;
    private String firstName;
    private String lastName;
    private Long partyId;
    private String partyName;
    private Long totalVotes;

}
