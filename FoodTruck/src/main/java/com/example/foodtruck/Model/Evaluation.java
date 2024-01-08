package com.example.foodtruck.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
//    @Column(columnDefinition = "varchar(225) not null")
    private String feedback;

    //new
//    private Integer a;
//    @Column(columnDefinition = "int not null")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "foodTruck_id",referencedColumnName = "id")
    @JsonIgnore
    private FoodTruck foodTruck;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

//new
//    @ManyToOne
//    @JoinColumn(name = "order_id",referencedColumnName = "id")
//    @JsonIgnore
//    private Orders orders;

}
