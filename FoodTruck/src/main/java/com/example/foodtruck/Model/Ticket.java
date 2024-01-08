package com.example.foodtruck.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MyTike")
public class Ticket {
    @Id
    private Integer id;

//    @Column(columnDefinition = "varchar(10) not null Check (status='Accept' or status='Reject') ")
    private String status;

    @ManyToOne
    @JoinColumn(name = "foodTruck_id",referencedColumnName = "id")
    @JsonIgnore
    private FoodTruck foodTruck;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Orders order;


    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
