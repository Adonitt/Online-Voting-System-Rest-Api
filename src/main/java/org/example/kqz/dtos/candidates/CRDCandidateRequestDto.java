package org.example.kqz.dtos.candidates;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.annotations.AtLeast18YearsOld;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.entities.enums.NationalityEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRDCandidateRequestDto {

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
    @AtLeast18YearsOld
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "Personal number must not be null")
    @NotBlank(message = "Personal number must not be empty")
    @Size(min = 10, max = 10, message = "Personal number must be 10 characters")
    private String personalNo;

    @NotNull(message = "Party id must not be null")
    private Long party;

    private Long candidateNumber;

    private String createdBy;

    private LocalDateTime createdAt;

    private NationalityEnum nationality;

    private MultipartFile photo;

}
