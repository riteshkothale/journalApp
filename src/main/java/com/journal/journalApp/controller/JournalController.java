package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private Map<Long,JournalEntry> journalEntryMap = new HashMap<>();

    @Autowired
    private JournalService journalService;

    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalService.createJournalEntry(journalEntry);
        return true;
    }

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalService.getAll();
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
        journalService.deleteById(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry newJournalEntry){

        JournalEntry old = journalService.findById(myId).orElse(null);
        if(old != null){
            old.setTitle(old.getTitle() != null && newJournalEntry.getTitle().equals("") ? newJournalEntry.getTitle():old.getTitle());
            old.setContent(old.getContent() != null && newJournalEntry.getTitle().equals("") ? newJournalEntry.getContent(): old.getContent());
        }
        journalService.createJournalEntry(old);
        return old;
    }
}
