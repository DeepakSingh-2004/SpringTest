package com.example.SpringTest.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Data
@Entity
@Table(name = "Doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String specialization;

    @Column (nullable = false)
    private Boolean available = true;




    public Boolean isAvailable() {
        return this.available;
    }



}
