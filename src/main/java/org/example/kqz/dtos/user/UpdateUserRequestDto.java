package org.example.kqz.dtos.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @NotNull(message = "Password must not be null")
    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,50}$", message = "Password must be at least 8 characters long, contain at least one letter and one number")
    private String password;

    @Past(message = "Birth date must be in the past")
    @NotNull(message = "Birth date must not be null")
    @NotBlank(message = "Birth date must not be empty")
    private LocalDate birthDate;

    @PastOrPresent(message = "Updated at must be in the past or present")
    @NotNull(message = "Updated at must not be null")
    @NotBlank(message = "Updated at must not be empty")
    private LocalDateTime updatedAt;
}