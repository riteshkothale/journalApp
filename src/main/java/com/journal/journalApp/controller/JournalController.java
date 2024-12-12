package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private Map<Long,JournalEntry> journalEntryMap = new HashMap<>();

    @Autowired
    private JournalService journalService;

    @PostMapping
    public ResponseEntity<ResponseEntity> createJournalEntry(@RequestBody JournalEntry journalEntry){
        try {
            journalEntry.setLocalDateTime(LocalDateTime.now());
            journalService.createJournalEntry(journalEntry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll(){
           List<JournalEntry> newList = journalService.getAll();
           if(newList.isEmpty()){
               return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<>(newList,HttpStatus.OK);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> optionalJournalEntry = journalService.findById(myId);
        if(optionalJournalEntry.isPresent()){
            return new ResponseEntity<>(optionalJournalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){
        journalService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry newJournalEntry){

        JournalEntry old = journalService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(old.getTitle() != null && !newJournalEntry.getTitle().equals("") ? newJournalEntry.getTitle():old.getTitle());
            old.setContent(old.getContent() != null && !newJournalEntry.getTitle().equals("") ? newJournalEntry.getContent(): old.getContent());
        }
        journalService.createJournalEntry(old);
        return new ResponseEntity<>(old,HttpStatus.OK);
    }
}
