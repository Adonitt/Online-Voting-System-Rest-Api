package org.example.kqz.dtos.votes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteRequestDto {
    @NotNull(message = "Party ID cannot be null")
    private Long partyId;

    @NotNull(message = "Candidate IDs cannot be null")
    @Size(min = 1, max = 10, message = "You must select between 1 and 10 candidates")
    private List<Long> candidateIds;

}
