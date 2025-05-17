package org.example.kqz.dtos.votes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.dtos.parties.CRDPartyRequestDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDto {
    private Long id;
    private String userEmail;
    private CRDPartyRequestDto party;
    private List<CRDCandidateRequestDto> candidates;
    private LocalDateTime timeStamp;
}
