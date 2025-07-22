package com.example.SpringTest.Service;

import com.example.SpringTest.Entity.AppointmentsEntity;
import com.example.SpringTest.Entity.DoctorEntity;
import com.example.SpringTest.Entity.PatientEntity;
import com.example.SpringTest.Repository.AppointmentsRepo;
import com.example.SpringTest.Repository.DoctorRepo;
import com.example.SpringTest.Repository.PatientRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
     private AppointmentsRepo appointmentsRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;


    public List<AppointmentsEntity> getAllAppointment(){
       List<AppointmentsEntity> appointment = appointmentsRepo.findAll();
       if (appointment.isEmpty()){
           logger.warn("NO APPOINTMENT FOUND IN THE DATABASE");
       }else {
           logger.info("Retrieved {} patients from the database.", appointment.size());
       }
       return appointment;
    }

    public ResponseEntity<AppointmentsEntity> getAppointmentById(Long id) {
        Optional<AppointmentsEntity> appointmentOptional = appointmentsRepo.findById(id);
        if (appointmentOptional.isPresent()){
            System.out.println("GETTING APPOINTMENT!");
            return  ResponseEntity.ok(appointmentOptional.get()); // 200 OK WITH BODY
        }else {
            System.out.println("NO APPOINTMENT FOUND!");
            return ResponseEntity.notFound().build(); // 404 NOT FOUND
        }
    }

    public AppointmentsEntity createAppointment(AppointmentsEntity appointmentRequest) {
        try {
            if (appointmentRequest.getPatientId() == null || appointmentRequest.getDoctorId() == null) {
                throw new RuntimeException("Patient ID or Doctor ID is missing from the request.");
            }

            Long patientId = appointmentRequest.getPatientId().getId();
            Long doctorId = appointmentRequest.getDoctorId().getId();

            System.out.println("Received patientId: " + patientId);
            System.out.println("Received doctorId: " + doctorId);

            PatientEntity patient = patientRepo.findById(patientId).orElse(null);
            DoctorEntity doctor = doctorRepo.findById(doctorId).orElse(null);

            if (patient == null || doctor == null) {
                throw new RuntimeException("Invalid doctor or patient ID");
            }

            // Re-assign managed entities
            appointmentRequest.setPatientId(patient);
            appointmentRequest.setDoctorId(doctor);

            return appointmentsRepo.save(appointmentRequest);
        } catch (Exception e) {
            e.printStackTrace(); // This will help you trace the root cause in the console
            throw new RuntimeException("Server error: " + e.getMessage());
        }
    }


    public ResponseEntity<AppointmentsEntity> updateAppointment(Long id, AppointmentsEntity appointmentsEntity) {
        Optional<AppointmentsEntity> existingAppointmentOptional = appointmentsRepo.findById(id);

        if (existingAppointmentOptional.isPresent()){
            AppointmentsEntity existingAppointment = existingAppointmentOptional.get();

            // Update fields
            existingAppointment.setPatientId(appointmentsEntity.getPatientId());
            existingAppointment.setDoctorId(appointmentsEntity.getDoctorId());
            existingAppointment.setDate(appointmentsEntity.getDate());

            AppointmentsEntity updated = appointmentsRepo.save(existingAppointment);
            return  ResponseEntity.ok(updated);
        }else {
            return  ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<AppointmentsEntity> deleteAppointmentById(Long id) {
       Optional<AppointmentsEntity> appointmentOptional = appointmentsRepo.findById(id);

       if (appointmentOptional.isPresent()){
           appointmentsRepo.deleteById(id);
           return ResponseEntity.ok().build();
       }else {
           return ResponseEntity.notFound().build();
       }
    }
}
