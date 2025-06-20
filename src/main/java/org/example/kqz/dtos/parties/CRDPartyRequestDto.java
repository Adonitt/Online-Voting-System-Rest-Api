package org.example.kqz.dtos.parties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.entities.CandidatesEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRDPartyRequestDto {
    @Positive
    private Long id;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotBlank(message = "Name must not be empty")
    @NotNull(message = "Name must not be null")
    private String name;

    @Size(min = 2, max = 50, message = "Abbreviation name must be between 2 and 50 characters")
    @NotNull(message = "Abbreviation name must not be null")
    @NotBlank(message = "Abbreviation name must not be empty")
    private String abbreviationName;

    private List<CandidatesEntity> candidates;


    @NotBlank(message = "Number of party must not be empty")
    @NotNull(message = "Number of party must not be null")
    private String numberOfParty;

//    private String symbol;

    @NotNull(message = "Description must not be null")
    @NotBlank(message = "Description must not be empty")
    private String description;

    private String createdBy;

    private LocalDateTime createdAt;

}
