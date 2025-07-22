package com.example.SpringTest.Controller;


import com.example.SpringTest.Exception.ResourceNotFoundException;
import com.example.SpringTest.Entity.EmployeeEntity;
import com.example.SpringTest.Repository.EmployeeRepository;
import com.example.SpringTest.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAll")
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //build create employee REST API
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.save(employee);
    }

    //build get employee by id REST API
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long id) {
        return employeeService.getById(id);
    }
    //build update employee REST API

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable long id, @RequestBody EmployeeEntity employeeDetails) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            Map<String, String> stringStringMap = new HashMap<>();
            stringStringMap.put("status", "failure");
            stringStringMap.put("message", "user not found");
            return ResponseEntity.ok(stringStringMap);
        }
        EmployeeEntity updateEmployee = optionalEmployee.get();
        updateEmployee.setFirstname(employeeDetails.getFirstname());
        updateEmployee.setLastname(employeeDetails.getLastname());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);

    }

    //build delete employee REST API

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<?> DeleteEmployee(@PathVariable long id) {

        EmployeeEntity deleteEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        employeeRepository.delete(deleteEmployee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
