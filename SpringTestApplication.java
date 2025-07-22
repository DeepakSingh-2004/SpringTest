package com.example.SpringTest;

import com.example.SpringTest.Entity.EmployeeEntity;
import com.example.SpringTest.Repository.EmployeeRepository;
import com.example.SpringTest.Repository.PatientRepo;
import com.example.SpringTest.Repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTestApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
		System.out.println("Spring Test");

	}


}
