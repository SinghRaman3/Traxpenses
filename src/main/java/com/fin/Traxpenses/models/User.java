package com.fin.Traxpenses.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String number;
    private String password;
    private LocalDate created_At;
    private LocalDate updated_At;
    private boolean active = true;
}
