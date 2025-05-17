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

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotBlank(message = "Name must not be empty")
    @NotNull(message = "Name must not be null")
    private String name;

    @Size(min = 3, max = 3, message = "Number of party must be 3 characters")
    @NotBlank(message = "Number of party must not be empty")
    @NotNull(message = "Number of party must not be null")
    private String numberOfParty;

    private String symbol;

}
