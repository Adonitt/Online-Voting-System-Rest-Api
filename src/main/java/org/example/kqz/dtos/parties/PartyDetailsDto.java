package org.example.kqz.dtos.parties;

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
public class PartyDetailsDto {
    private Long id;

    private String name;

    private String abbreviationName;

    private List<CandidatesEntity> candidates;

    private String numberOfParty;

    private MultipartFile symbol;

    private String description;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;
}