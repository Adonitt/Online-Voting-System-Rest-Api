package org.example.kqz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "parties")
public class PartyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="party_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "party_id", fetch = FetchType.LAZY)
    private List<CandidatesEntity> candidates;

    @Column(name = "numberOfParty", nullable = false, unique = true)
    private String numberOfParty;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "totalVotes", nullable = false)
    private int totalVotes;
}
