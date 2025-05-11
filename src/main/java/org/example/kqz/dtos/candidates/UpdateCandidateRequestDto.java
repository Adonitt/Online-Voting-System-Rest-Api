package org.example.kqz.dtos.candidates;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.kqz.annotations.AtLeast18YearsOld;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.entities.enums.NationalityEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCandidateRequestDto {
    @Positive
    private Long id;

    @NotBlank(message = "First name must not be empty")
    @NotNull(message = "First name must not be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Birth date must not be null")
    @NotBlank(message = "Birth date must not be empty")
    @AtLeast18YearsOld
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "Personal number must not be null")
    @NotBlank(message = "Personal number must not be empty")
    @Size(min = 10, max = 10, message = "Personal number must be 10 characters")
    private String personalNo;

    private NationalityEnum nationality;

    @NotNull(message = "Party id must not be null")
    private PartyEntity party_id;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
