package org.example.kqz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "votes")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidatesEntity candidate;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private PartyEntity party;

    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp = LocalDateTime.now();
}
