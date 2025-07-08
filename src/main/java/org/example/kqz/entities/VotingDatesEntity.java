package org.example.kqz.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "voting_dates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingDatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "party_creation_deadline")
    private LocalDate partyCreationDeadline;

    @Column(name = "voting_day")
    private LocalDate votingDay;
}
