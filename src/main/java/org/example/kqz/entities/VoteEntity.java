package org.example.kqz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private PartyEntity party;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vote_candidates",  // Tabela lidhëse
            joinColumns = @JoinColumn(name = "vote_id"),  // Referencë nga vote_id
            inverseJoinColumns = @JoinColumn(name = "candidate_id")  // Referencë nga candidate_id
    )
    private List<CandidatesEntity> candidates;

    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp = LocalDateTime.now();
}
