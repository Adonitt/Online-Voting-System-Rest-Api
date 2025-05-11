package org.example.kqz.dtos.parties;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.kqz.entities.CandidatesEntity;

import java.time.LocalDateTime;
import java.util.List;

public class CrudPartyRequestDto {
    @Positive
    private Long id;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotBlank(message = "Name must not be empty")
    @NotNull(message = "Name must not be null")
    private String name;

    @NotNull(message = "Candidates must not be null")
    @NotBlank(message = "Candidates must not be empty")
    private List<CandidatesEntity> candidates;

    @Size(min = 3, max = 3, message = "Number of party must be 3 characters")
    @NotBlank(message = "Number of party must not be empty")
    @NotNull(message = "Number of party must not be null")
    private String numberOfParty;

    private String symbol;

    @NotNull(message = "Description must not be null")
    @NotBlank(message = "Description must not be empty")
    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;


}
