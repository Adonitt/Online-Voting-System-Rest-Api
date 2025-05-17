package org.example.kqz.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.entities.VoteEntity;
import org.example.kqz.entities.enums.CityEnum;
import org.example.kqz.entities.enums.NationalityEnum;
import org.example.kqz.entities.enums.RoleEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {


    private Long id;

    private String personalNo;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDate;

    private LocalDateTime registeredAt;

    private boolean hasVoted = false;

    private RoleEnum role;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private NationalityEnum nationality;

    private CityEnum city;

}
