package org.example.kqz.dtos.user;

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
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserRequestDto {
    @Positive
    private Long id;

    @Positive(message = "Personal number must be positive")
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

    @NotNull(message = "Password must not be null")
    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one number")
    private String password;

    @NotNull(message = "Confirm password must not be null")
    @NotBlank(message = "Confirm password must not be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Confirm Password must contain at least one uppercase letter, one lowercase letter, and one number")
    private String confirmPassword;

    @Past(message = "Birth date must be in the past")
    @NotNull(message = "Birth date must not be null")
    @AtLeast18YearsOld()
    private LocalDate birthDate;

    @PastOrPresent(message = "Registered at must be in the past or present")
    private LocalDateTime registeredAt = LocalDateTime.now();

    private boolean hasVoted = false;

    private RoleEnum role;

    private NationalityEnum nationality;
}