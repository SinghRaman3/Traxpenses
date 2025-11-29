package com.fin.Traxpenses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpenseUpdateDTO {
    private String description;
    private LocalDateTime date;
    private Double amount;
    private String category;
}
