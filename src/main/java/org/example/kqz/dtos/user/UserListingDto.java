package org.example.kqz.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kqz.entities.enums.CityEnum;
import org.example.kqz.entities.enums.RoleEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserListingDto {

    private Long id;

    private String personalNo;

    private String firstName;

    private String lastName;

    private String email;

    private boolean hasVoted = false;

    private RoleEnum role;
    private CityEnum city;

}
