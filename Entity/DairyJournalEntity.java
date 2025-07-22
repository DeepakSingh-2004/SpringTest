package com.example.SpringTest.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class DairyJournalEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  long id;

        private String title;

        private String content;

        private LocalDate createdDate;




}
