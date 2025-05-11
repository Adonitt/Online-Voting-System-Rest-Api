package org.example.kqz.repositories;


import org.apache.catalina.User;
import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPersonalNo(String personalNo);

    Optional<UserEntity> findByEmailAndRole(String email, RoleEnum role);


}
