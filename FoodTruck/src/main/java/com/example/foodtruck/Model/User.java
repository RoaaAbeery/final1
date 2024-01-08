package com.example.foodtruck.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "MyUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NotEmpty(message = "name should not be empty")
//    @Column(columnDefinition = "varchar(50) not null")
//    @Pattern(regexp = "^[a-zA-Z ]+$")
    private  String userName;
    private String password;
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Email should not be empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private  String email;
//    @NotEmpty(message = "phone should not be empty")
//    @Pattern(regexp = "^05\\d{8}$", message = "Invalid phone number format")
    @Column(columnDefinition = "varchar(11) not null ")
    private String phone;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Set<Orders> orders;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Profile profile;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    private Set<Ticket> ticket;
    @ManyToOne
    @JoinColumn(name = "evaluation_id",referencedColumnName = "id")
    @JsonIgnore
    private Evaluation evaluation;
}
