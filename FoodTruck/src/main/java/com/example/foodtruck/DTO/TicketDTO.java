package com.example.foodtruck.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO {

//    @NotEmpty(message = "status should not be empty")
//    @Pattern(regexp = "^(Accept|Reject)$",message = "status must be Accept or Reject")
    private String status;
    private Integer user_id;
    private Integer order_id;
    private Integer food_id;


}
