package com.fin.Traxpenses.dto;

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
    private Double amount;
    private String category;
}
