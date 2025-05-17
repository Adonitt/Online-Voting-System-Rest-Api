package org.example.kqz.repositories;

import org.example.kqz.dtos.candidates.UpdateCandidateRequestDto;
import org.example.kqz.entities.CandidatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatesRepository extends JpaRepository<CandidatesEntity, Long> {
    boolean existsByPersonalNo(String personalNo);

    @Query("SELECT COALESCE(MAX(c.candidateNumber), 0) FROM candidates c WHERE c.party.id = :partyId")
    Long findMaxCandidateNumberByPartyId(@Param("partyId") Long partyId);

    Optional<CandidatesEntity> findByCandidateNumber(Long candidateNumber);

    Optional<CandidatesEntity> findByPartyIdAndCandidateNumber(Long partyId, Long candidateNumber);

}
