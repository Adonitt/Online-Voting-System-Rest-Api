package org.example.kqz.dtos.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.annotations.AtLeast18YearsOld;
import org.example.kqz.entities.enums.NationalityEnum;
import org.example.kqz.entities.enums.RoleEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRequestDto {
    @Positive
    private Long id;

    @Positive(message = "Personal number must be positive")
    @Size(min = 10, max = 10, message = "Personal number must be 10 characters")
    @NotBlank(message = "Personal number must not be empty")
    @NotNull(message = "Personal number must not be null")
    private String personalNo;

    @NotBlank(message = "First name must not be empty")
    @NotNull(message = "First name must not be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    @NotNull(message = "Email must not be null")
    private String email;


    @Past(message = "Birth date must be in the past")
    @NotNull(message = "Birth date must not be null")
    @NotBlank(message = "Birth date must not be empty")
    @AtLeast18YearsOld()
    private LocalDate birthDate;

    @PastOrPresent(message = "Registered at must be in the past or present")
    @NotNull(message = "Registered at must not be null")
    @NotBlank(message = "Registered at must not be empty")
    private LocalDateTime registeredAt;

    private NationalityEnum nationality;

    private boolean hasVoted = false;

    private RoleEnum role;


}
