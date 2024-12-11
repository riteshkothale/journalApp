package com.journal.journalApp.controller;

import com.journal.journalApp.entity.JournalEntry;
import com.journal.journalApp.entity.JournalEntry_Map;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalController_Map {

    private Map<Long, JournalEntry_Map> journalEntryMap = new HashMap<>();

    @GetMapping
    public List<JournalEntry_Map> getAll(){
        return new ArrayList<>(journalEntryMap.values());
    }

    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry_Map journalEntry){
        journalEntryMap.put(journalEntry.getId(),journalEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry_Map getJournalEntryById(@PathVariable Long myId){
        return journalEntryMap.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable Long myId){
        journalEntryMap.remove(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry_Map updateJournalEntry(@PathVariable Long myId, @RequestBody JournalEntry_Map journalEntry){
        return journalEntryMap.put(myId,journalEntry);
    }
}
