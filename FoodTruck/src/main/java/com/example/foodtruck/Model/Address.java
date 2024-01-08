package com.example.foodtruck.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MyAddress")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String address;
    private String startDate;
    private Integer NumberWeek;
    private String city;
    private String street;


//    @ManyToOne
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
//    @JsonIgnore
//    private User user;

    @ManyToOne
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    @JsonIgnore
    private Profile profile;
    @OneToMany
    @JsonIgnore
    private Set<Orders> orders;
}
