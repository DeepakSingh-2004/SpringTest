package com.example.SpringTest.Repository;

import com.example.SpringTest.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {


    //ALL CRUD DATABASE METHODS
}
