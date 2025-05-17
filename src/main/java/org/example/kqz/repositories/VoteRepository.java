package org.example.kqz.repositories;

import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
    boolean existsByUser(UserEntity user);
}
