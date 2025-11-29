package com.fin.Traxpenses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpenseResponseDTO {
    private UUID id;
    private String description;
    private Double amount;
    private String category;
    private LocalDate date;
}
