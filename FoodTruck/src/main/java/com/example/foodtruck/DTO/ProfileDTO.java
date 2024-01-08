package com.example.foodtruck.DTO;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ProfileDTO {
    private String description;
    private LocalDateTime AccountCreationDate =LocalDateTime.now();

    private Integer user_id;
    private Integer address_id;
}
