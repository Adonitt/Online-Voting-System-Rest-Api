package org.example.kqz.dtos.candidates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateListingDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Long party;

    private Long candidateNumber;

}
