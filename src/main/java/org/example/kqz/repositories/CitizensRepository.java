package org.example.kqz.repositories;

import org.example.kqz.entities.CitizensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitizensRepository extends JpaRepository<CitizensEntity, Long> {
    Optional<CitizensEntity> findByPersonalNo(String personalNo);

    List<CitizensEntity> findAllByPersonalNo(String personalNo);

    boolean existsByPersonalNoAndFirstNameAndLastNameAndBirthDate(String personalNo, String firstName, String lastName, LocalDate birthDate);
}
