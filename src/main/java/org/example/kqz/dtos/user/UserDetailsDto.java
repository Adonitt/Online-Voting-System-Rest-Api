package org.example.kqz.dtos.user;

import org.example.kqz.entities.VoteEntity;
import org.example.kqz.entities.enums.RoleEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserDetailsDto {


    private Long id;

    private String personalNo;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private LocalDateTime registeredAt;

    private boolean hasVoted = false;

    private RoleEnum role;

    private List<VoteEntity> votes;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private LocalDateTime deletedAt;

}
