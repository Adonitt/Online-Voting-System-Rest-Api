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

    private String user; // will hold user's email

    private Long party;

    private List<Long> candidates;

    private LocalDateTime timeStamp;

}