package org.example.kqz.dtos.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.annotations.AtLeast18YearsOld;
import org.example.kqz.entities.enums.CityEnum;
import org.example.kqz.entities.enums.NationalityEnum;
import org.example.kqz.entities.enums.RoleEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {
    @Positive
    private Long id;

    private boolean hasVoted = false;

    private RoleEnum role;

    private NationalityEnum nationality;

    private CityEnum city;

}