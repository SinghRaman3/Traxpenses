package com.fin.Traxpenses.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginDTO {
    @NotBlank(message = "Number cannot be blank")
    private String number;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
