package com.example.SpringTest.Repository;

import com.example.SpringTest.Entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<DoctorEntity, Long>{
    List<DoctorEntity> findByAvailableTrue();
}
