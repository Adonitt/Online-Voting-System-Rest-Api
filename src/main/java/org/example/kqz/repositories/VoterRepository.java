package org.example.kqz.repositories;

import org.example.kqz.entities.AllowedVotersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<AllowedVotersEntity, Long> {
}
