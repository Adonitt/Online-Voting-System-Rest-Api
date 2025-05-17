package org.example.kqz.dtos.votes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteRequestDto {

    @NotNull
    private Long party;

    @NotNull
    @Size(min = 1, max = 10)
    private List<Long> candidates;
}
