package org.example.kqz.dtos.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVoteDto {
    private Long userId;
    private String user;
    private Long partyId;
    private String party;
    private List<String> candidateNames;
    private LocalDateTime voteTimestamp;
}
