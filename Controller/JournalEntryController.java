package com.example.SpringTest.Controller;

import com.example.SpringTest.Entity.JournalEntity;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntity> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntity>GetAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity myEntry){
        try {
            journalEntries.put(myEntry.getId(), myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable Long myId){
        JournalEntity journalEntity = journalEntries.get(myId);
        if (journalEntity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }return new ResponseEntity<>(journalEntity, HttpStatus.OK);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable Long myId){
    journalEntries.remove(myId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable Long id, @RequestBody JournalEntity myEntry){
   try {
       journalEntries.put(id, myEntry);
       return new ResponseEntity<>(HttpStatus.OK);
   }catch (Exception e){
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
    }
}
