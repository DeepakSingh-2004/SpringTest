package com.example.SpringTest.Entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data


@Table (name = "employees")

public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;


    @Column(name = "email_id ")
    private String emailId;


}
