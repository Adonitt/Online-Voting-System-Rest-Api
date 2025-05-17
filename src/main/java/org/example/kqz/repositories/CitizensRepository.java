package org.example.kqz.repositories;

import org.example.kqz.entities.CitizensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizensRepository extends JpaRepository<CitizensEntity, Long> {
    Optional<CitizensEntity> findByPersonalNo(String personalNo);

    boolean existsByPersonalNoAndFirstNameAndLastName(String personalNo, String firstName, String lastName);
}
