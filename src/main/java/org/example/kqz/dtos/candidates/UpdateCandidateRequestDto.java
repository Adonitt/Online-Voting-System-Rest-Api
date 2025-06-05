package org.example.kqz.dtos.candidates;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.kqz.annotations.AtLeast18YearsOld;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.entities.enums.NationalityEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCandidateRequestDto {
    @Positive
    private Long id;

    @NotNull(message = "Birth date must not be null")
    @AtLeast18YearsOld
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    private NationalityEnum nationality;

    @NotNull(message = "Party id must not be null")
    private Long party;

    @NotNull(message = "Candidate number must not be null")
    private Long candidateNumber;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//    private MultipartFile photo;

}
