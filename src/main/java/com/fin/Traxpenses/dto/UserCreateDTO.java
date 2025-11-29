package com.fin.Traxpenses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateDTO {
    private String name;
    private String email;
    private String number;
    private String password;
    private LocalDate created_At;
    private LocalDate updated_At;
    private boolean active;
}
