package com.example.SpringTest.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class JournalEntity {

    @Id
    private  long id;

    private String title;

    private String content;



}
