package com.example.SpringTest.Repository;

import com.example.SpringTest.Entity.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsRepo extends JpaRepository<AppointmentsEntity, Long> {
}
