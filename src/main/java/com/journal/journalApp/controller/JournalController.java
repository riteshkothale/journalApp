package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        journalService.createJournalEntry(journalEntry);
        return true;
    }

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalService.getAll();
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntryMap.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable Long myId){

        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntry(@PathVariable Long myId, @RequestBody JournalEntry journalEntry){
        return journalEntryMap.put(myId,journalEntry);
    }
}
