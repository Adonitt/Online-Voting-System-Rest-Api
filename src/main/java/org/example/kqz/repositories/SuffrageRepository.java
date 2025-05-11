package org.example.kqz.repositories;

import org.example.kqz.entities.SuffrageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuffrageRepository extends JpaRepository<SuffrageEntity, Long> {
    Optional<SuffrageEntity> findByPersonalNo(String personalNo);

    boolean existsByPersonalNoAndFirstNameAndLastName(String personalNo, String firstName, String lastName);
}
