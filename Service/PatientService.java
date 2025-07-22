package com.example.SpringTest.Service;

import com.example.SpringTest.Entity.PatientEntity;
import com.example.SpringTest.Repository.PatientRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepo patientRepo;


    //OPTION_1
//    public List<PatientEntity> viewAllPatient() {
//        return patientRepo.findAll();
//}


    //OPTION_2
    public List<PatientEntity> getAllPatient() {
        List<PatientEntity> patients = patientRepo.findAll();

        if (patients.isEmpty()) {
            logger.warn("No patients found in the database.");
        } else {
            logger.info("Retrieved {} patients from the database.", patients.size());
        }

        return patients;
    }


    public PatientEntity getPatientById(Long id){
        try {
            Optional<PatientEntity> patientEntity = patientRepo.findById(id);
            return  patientEntity.orElse(null);
        } catch (Exception e) {
            logger.error("AN ERROR OCCURRED WHILE FETCHING THE PATIENT DETAILS BY ID{}: {}", id, e.getMessage(), e);
            return  null;
        }

    }





    //OPTION_1
//    public PatientEntity addPatient(PatientEntity patient) {
//        return patientRepo.save(patient);
//}

   //OPTION_2
    public PatientEntity addPatient(PatientEntity patient) {
        try{
            logger.info("TRYING TO ADD THE PATIENT DETAILS: {}", patient.getName());
            return patientRepo.save(patient);
        } catch (Exception e) {
            logger.error("AN ERROR OCCURRED WHILE ADDING THE PATIENT DETAILS: {}", e.getMessage(), e);
            throw e;
        }

    }


      //OPTION_1
//    public Optional<PatientEntity> checkPatientById(Long id) {
//        return patientRepo.findById(id);
//}

    //OPTION_2
    public Optional<PatientEntity> checkPatientById(Long id) {
        logger.info("Checking for patient with ID: {}", id);

        Optional<PatientEntity> patient = patientRepo.findById(id);

        if (patient.isPresent()) {
            logger.info("Patient found with ID: {}", id);
        } else {
            logger.warn("No patient found with ID: {}", id);
        }

        return patient;
    }


     //OPTION_1
//    public void deletePatientById(Long id){
//        patientRepo.deleteById(id);
//}

    //OPTION_2
    public void deletePatientById(Long id) {
        Optional<PatientEntity> existingPatient = patientRepo.findById(id);

        if (existingPatient.isPresent()) {
            patientRepo.deleteById(id);
            logger.info("Patient with ID {} deleted successfully.", id);
        } else {
            logger.warn("Patient with ID {} not found. Delete operation skipped.", id);
        }
    }


    public PatientEntity updatebyId(Long id, PatientEntity updatedPatient){
        try {

            Optional<PatientEntity> existingPatient = patientRepo.findById(id);
            if (existingPatient.isPresent()){
                PatientEntity p = existingPatient.get();
                p.setName(updatedPatient.getName());
                p.setAge(updatedPatient.getAge());
                p.setGender(updatedPatient.getGender());

              return   patientRepo.save(p);


            }
        } catch (Exception e) {
            logger.error("AN ERROR OCCURRED WHILE UPDATING PATIENT WITH ID {}: {}", id, e.getMessage(), e);
            return  null;
        }
            return null;
    }


//    public ResponseEntity<PatientEntity> updatebyId(Long id) {
//        return null;
//    }
}
