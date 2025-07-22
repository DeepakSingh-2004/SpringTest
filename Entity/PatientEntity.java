package com.example.SpringTest.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "Patients")
public class PatientEntity {

    @Id // use this @Id to mark the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue is used to auto-generate the value

    private long id;
    private String name;
    private String age ;
    private String gender;
    private String disease;


}
