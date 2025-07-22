package com.example.SpringTest.Controller;

import com.example.SpringTest.Entity.DoctorEntity;
import com.example.SpringTest.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @GetMapping("/available")
    public List<DoctorEntity> viewAvailableDoctor(){
        return doctorService.viewAvaiDoctor();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DoctorEntity> checkDoctorById(@PathVariable Long id){
        return doctorService.checkDoctorById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DoctorEntity doctorEntity) {
        return doctorService.create(doctorEntity);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorEntity> updateDoctorById(@PathVariable Long id, @RequestBody DoctorEntity doctorEntity){
        System.out.println("UPDATING DOCTOR ");
        return doctorService.updateDoctorById(id, doctorEntity);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DoctorEntity> deletingDoctorById(@PathVariable Long id){
        System.out.println("DELETING DOCTOR !");
        doctorService.deletingDoctorById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
