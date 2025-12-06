package com.fin.Traxpenses.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be of valid format")
    private String email;

    @NotBlank(message = "Number cannot be blank")
    private String number;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private LocalDate created_At;
    private LocalDate updated_At;
    private boolean active;
}
