package org.example.kqz.dtos.votes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequestDto {
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Party ID cannot be null")
    private Long partyId;

    @NotNull(message = "Candidate IDs cannot be null")
    @Size(min = 1, message = "At least one candidate must be selected")
    private List<Long> candidateIds;

}
