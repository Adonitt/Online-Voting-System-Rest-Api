package org.example.kqz.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDto {

    @NotNull(message = "Personal number must not be null")
    @NotBlank(message = "Personal number must not be empty")
    @Size(min = 10, max = 10, message = "Personal number must be 10 characters")
    private String personalNo;

    @NotNull(message = "Password must not be null")
    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,50}$", message = "Password must be at least 8 characters long, contain at least one letter and one number")
    private String password;
}
