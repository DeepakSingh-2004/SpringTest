package com.example.SpringTest.Controller;

import com.example.SpringTest.Entity.AppointmentsEntity;
import com.example.SpringTest.Service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.AppEvent;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentsController {

    @Autowired
    private  AppointmentsService appointmentsService;


    @GetMapping
    public List<AppointmentsEntity> getAllAppointment(){
        System.out.println("Fetching the Appointments");
        return appointmentsService.getAllAppointment();
    }


    @GetMapping("/appointment/{id}")
    public ResponseEntity <AppointmentsEntity> getAppointmentById(@PathVariable Long id){
        System.out.println("FETCHING APPOINTMENT BY ID");
        return appointmentsService.getAppointmentById(id);
    }


    @PostMapping("/create")
    public AppointmentsEntity createAppointment(@RequestBody AppointmentsEntity appointmentRequest) {
        System.out.println("CREATING APPOINTMENT");
        return appointmentsService.createAppointment(appointmentRequest);
}


    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentsEntity> updateAppointment(@PathVariable Long id, @RequestBody AppointmentsEntity appointmentsEntity){
        System.out.println("UPDATING APPOINTMENT!");
        return appointmentsService.updateAppointment(id, appointmentsEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AppointmentsEntity> deleteAppointmentById(@PathVariable Long id){
        System.out.println("DELETING THE APPOINTMENT!");
         appointmentsService.deleteAppointmentById(id);
         return ResponseEntity.noContent().build();
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
    return principal.getName();
    }
}
