package com.fin.Traxpenses.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseCreateDTO {
    private String description;
    private LocalDateTime date;
    @NotNull(message =  "Please enter an amount")
    private Double amount;
    private String category;
}
