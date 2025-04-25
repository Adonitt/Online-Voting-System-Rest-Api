package org.example.kqz.dtos.candidates;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.kqz.annotations.AtLeast18YearsOld;
import org.example.kqz.entities.PartyEntity;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCandidateRequestDto {
    @Positive
    private Long id;

    @NotNull(message = "Birth date must not be null")
    @NotBlank(message = "Birth date must not be empty")
    @AtLeast18YearsOld
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "Party id must not be null")
    private PartyEntity party_id;
}
