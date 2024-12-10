package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private Map<Long,JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntryMap.values());
    }

    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntryMap.put(journalEntry.getId(),journalEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntryMap.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable Long myId){
        journalEntryMap.remove(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntry(@PathVariable Long myId, @RequestBody JournalEntry journalEntry){
        return journalEntryMap.put(myId,journalEntry);
    }
}
