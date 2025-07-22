package com.example.SpringTest.Controller;

import com.example.SpringTest.Entity.DoctorEntity;
import com.example.SpringTest.Entity.PatientEntity;
import com.example.SpringTest.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
     private PatientService patientService;

    @GetMapping("/getAll")
    public List<PatientEntity> viewAllPatients() {
        System.out.println("Fetching the patients details");
        return patientService.getAllPatient();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<PatientEntity> checkPatientById(@PathVariable Long id) {
        System.out.println("Fetching details by ID");
        return patientService.checkPatientById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/id/{id}")
    public PatientEntity addPatience(@RequestBody PatientEntity patient) {
        System.out.println("Adding patient information");
        return patientService.addPatient(patient);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<PatientEntity> updatebyId(@PathVariable Long id, @RequestBody PatientEntity updatedPatient){
        System.out.println("UPDATING DOCTOR BY ID");

        PatientEntity updated = patientService.updatebyId(id, updatedPatient);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
     public ResponseEntity<String> deletePatientById (@PathVariable Long id){
        Optional<PatientEntity> existingPatient = patientService.checkPatientById(id);
        {
            if (existingPatient.isPresent()) {
                patientService.deletePatientById(id);
                return ResponseEntity.ok("Patient with ID " + id + " has been deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + id + " not found.");
            }
        }
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

}