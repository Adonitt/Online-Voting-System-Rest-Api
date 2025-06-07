package org.example.kqz.dtos.candidates;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.entities.enums.NationalityEnum;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCandidateRequestDto {
    @Positive
    private Long id;

    private NationalityEnum nationality;

    @NotNull(message = "Party id must not be null")
    private Long party;

    private Long candidateNumber;


    private String updatedBy;


    private LocalDateTime updatedAt;

//    private MultipartFile photo;

}
