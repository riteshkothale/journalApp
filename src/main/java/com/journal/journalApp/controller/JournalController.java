package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.entity.User;
import com.journal.journalApp.service.JournalService;
import com.journal.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private Map<Long,JournalEntry> journalEntryMap = new HashMap<>();

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @PostMapping("{userName}")
    public ResponseEntity<ResponseEntity> createJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable String userName){
        try {
            journalService.createJournalEntry(journalEntry, userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntry(@PathVariable String userName){
        User user = userService.findByUserName(userName);
           List<JournalEntry> allList = user.getJournalEntries();
           if(!allList.isEmpty() && allList != null){
               return new ResponseEntity<>(allList, HttpStatus.OK);
           }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> optionalJournalEntry = journalService.findById(myId);
        if(optionalJournalEntry.isPresent()){
            return new ResponseEntity<>(optionalJournalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId, @PathVariable String userName){
        journalService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry newJournalEntry){

        JournalEntry old = journalService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(old.getTitle() != null && !newJournalEntry.getTitle().equals("") ? newJournalEntry.getTitle():old.getTitle());
            old.setContent(old.getContent() != null && !newJournalEntry.getTitle().equals("") ? newJournalEntry.getContent(): old.getContent());
        }
        journalService.updateJournalEntry(old);
        return new ResponseEntity<>(old,HttpStatus.OK);
    }
}
