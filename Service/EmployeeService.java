package com.example.SpringTest.Service;

import com.example.SpringTest.Entity.EmployeeEntity;
import com.example.SpringTest.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService  {


    @Autowired
    private EmployeeRepository employeeRepository;


    public ResponseEntity<?> save(EmployeeEntity employee) {
         employee = employeeRepository.save(employee);
         return ResponseEntity.ok(employee);
    }


    public ResponseEntity<?> getById(long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            Map<String, String> stringStringMap = new HashMap<>();
            stringStringMap.put("status", "failure");
            stringStringMap.put("message", "user not found");
            return ResponseEntity.ok(stringStringMap);
        }
        return ResponseEntity.ok(employee);
    }
}
