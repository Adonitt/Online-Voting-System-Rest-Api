package org.example.kqz.dtos.parties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyListingDto {
    @Positive
    private Long id;

    private String name;

    private String abbreviationName;

    private String numberOfParty;

    private String symbol;

}
