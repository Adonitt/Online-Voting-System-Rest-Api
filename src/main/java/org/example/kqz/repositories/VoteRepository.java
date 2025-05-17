package org.example.kqz.repositories;

import org.example.kqz.dtos.results.CandidateVoteResultDto;
import org.example.kqz.dtos.results.PartyVoteResultsDto;
import org.example.kqz.dtos.results.UserVoteDto;
import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    boolean existsByUser(UserEntity user);
    List<VoteEntity> findByPartyId(Long partyId);

    // Find all votes for a candidate (via join)
    List<VoteEntity> findByCandidatesId(Long candidateId);

    // Find all votes by user
    List<VoteEntity> findByUserId(Long userId);

    // Find all votes (to aggregate globally)
    List<VoteEntity> findAll();


}
