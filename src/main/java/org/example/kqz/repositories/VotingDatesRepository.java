package org.example.kqz.repositories;

import org.example.kqz.entities.VotingDatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingDatesRepository extends JpaRepository<VotingDatesEntity, Long> {
    VotingDatesEntity findTopByOrderByIdDesc();
}
