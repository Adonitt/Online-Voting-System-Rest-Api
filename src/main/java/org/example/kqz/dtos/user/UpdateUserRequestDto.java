package org.example.kqz.dtos.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @NotBlank(message = "Email must not be empty")
    @NotNull(message = "Email must not be null")
    @Email(message = "Invalid email format")
    private String email;

    @Past(message = "Birth date must be in the past")
    @NotNull(message = "Birth date must not be null")
    private LocalDate birthDate;

    private LocalDateTime updatedAt = LocalDateTime.now();

    private String updatedBy;

    private NationalityEnum nationality;

    private RoleEnum role;


}