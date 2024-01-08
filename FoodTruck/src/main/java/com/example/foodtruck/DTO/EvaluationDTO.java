package com.example.foodtruck.DTO;

import com.example.foodtruck.Model.FoodTruck;
import com.example.foodtruck.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EvaluationDTO {

    private String feedback;
    private Integer rating;
    //new
//    private Integer a;
    private Integer user_id;
    private Integer foodTruck_id;
}
