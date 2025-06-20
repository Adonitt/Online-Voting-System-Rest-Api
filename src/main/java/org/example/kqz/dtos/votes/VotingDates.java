package org.example.kqz.dtos.votes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotingDates {
    public static final LocalDate PARTY_CREATION_DEADLINE = LocalDate.of(2025, 6, 25);
    public static final LocalDate VOTING_DAY = LocalDate.of(2025, 6, 26);

    private LocalDate partyCreationDeadline;
    private LocalDate votingDay;
}
