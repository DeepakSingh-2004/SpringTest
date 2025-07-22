package com.example.SpringTest.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@Table(name ="Appointments")
public class AppointmentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patients_id")
    private PatientEntity patientId;

    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private DoctorEntity doctorId;

//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate date;

    @Column(name = "date")
    private LocalDate date;


    public Object getPatientsId() {
        return  patientId != null ? patientId.getId() : null;
    }

    public Object getDoctorsId() {
        return  doctorId != null? doctorId.getId() : null;
    }
}
