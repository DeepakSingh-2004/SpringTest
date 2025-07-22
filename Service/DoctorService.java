package com.example.SpringTest.Service;

import com.example.SpringTest.Entity.DoctorEntity;
import com.example.SpringTest.Repository.DoctorRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@Service
public class DoctorService {


    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepo doctorRepo;


    public List<DoctorEntity> viewAvaiDoctor() {
        List<DoctorEntity> doctor = doctorRepo.findByAvailableTrue();

        if (doctor.isEmpty()) {
            logger.warn("No patients found in the database.");
        } else {
            logger.info("Retrieved {} patients from the database.", doctor.size());
        }
        return doctor;
    }


    public Optional<DoctorEntity> checkDoctorById(Long id) {
        try {
            return doctorRepo.findById(id);
        } catch (Exception e) {
            logger.error("Error fetching doctor with ID {}: {}", id, e.getMessage(), e);
            return Optional.empty();  // Return empty Optional if error occurs
        }
    }


    public ResponseEntity<DoctorEntity> create(DoctorEntity doctorEntity) {
        try {
            logger.info("TRYING TO ADD THE PATIENT DETAILS: {}", doctorEntity.getName());
            return ResponseEntity.ok(doctorEntity);
        } catch (Exception e) {
            logger.error("AN ERROR OCCURRED WHILE CREATING THE DOCTOR DETAILS: {}", e.getMessage(), e);
            throw e;
        }
    }


    public ResponseEntity<DoctorEntity> updateDoctorById(Long id, DoctorEntity updatedDoctor) {
        Optional<DoctorEntity> existingDoctorOptional = doctorRepo.findById(id);

        if (existingDoctorOptional.isPresent()) {
            DoctorEntity existingDoctor = existingDoctorOptional.get();

            // Update only relevant fields
            existingDoctor.setName(updatedDoctor.getName());
            existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
            existingDoctor.setAvailable(updatedDoctor.isAvailable());

            //Save updated doctor to database
            DoctorEntity savedDoctor = doctorRepo.save(existingDoctor);

            return ResponseEntity.ok(savedDoctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public void deletingDoctorById(Long id) {

        Optional<DoctorEntity> existingDoctor = doctorRepo.findById(id);

        if (existingDoctor.isPresent()) {
            doctorRepo.deleteById(id);
            logger.info("\"Doctor with ID {} deleted successfully.", id);
        } else {
            logger.warn("Patient with ID {} not found. Delete operation skipped.", id);
        }
    }


}
