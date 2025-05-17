package org.example.kqz.repositories;

import org.example.kqz.entities.CandidatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatesRepository extends JpaRepository<CandidatesEntity, Long> {
    boolean existsByPersonalNo(String personalNo);
}
