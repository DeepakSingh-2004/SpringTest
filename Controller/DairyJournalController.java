package com.example.SpringTest.Controller;

import com.example.SpringTest.Entity.DairyJournalEntity;
import com.example.SpringTest.Entity.JournalEntity;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dairy_journal")
public class DairyJournalController {

public Map<Long, DairyJournalEntity> DairyJournalEntries = new HashMap<>();

    @GetMapping
    public List<DairyJournalEntity> GetAll(){
    return new ArrayList<>(DairyJournalEntries.values());
}

    @PostMapping("/id/{id}")
    public ResponseEntity<String> createEntry(@RequestBody DairyJournalEntity entry){
        entry.setCreatedDate(LocalDate.now());
        DairyJournalEntries.put(entry.getId(), entry);
        return ResponseEntity.ok("Entry Created");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DairyJournalEntity> GetById (@PathVariable Long id){
        DairyJournalEntity entry = DairyJournalEntries.get(id);
        if (entry == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody DairyJournalEntity newEntry){
        if (!DairyJournalEntries.containsKey(id)) {
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        } DairyJournalEntries.put(id, newEntry);
        return ResponseEntity.ok("Updated");
    }


    public ResponseEntity<String> delete(@PathVariable Long id){
        if (DairyJournalEntries.remove(id) == null){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok("Deleted");
    }
}
