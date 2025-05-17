package org.example.kqz.dtos.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.entities.enums.CityEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityVoteSummaryDto {
    CityEnum city;
    String partyName;
    long totalVotes;
    double percentage;

}
