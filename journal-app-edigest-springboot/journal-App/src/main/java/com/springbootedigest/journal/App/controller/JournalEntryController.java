package com.springbootedigest.journal.App.controller;

import com.springbootedigest.journal.App.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journals")
public class JournalEntryController {

    private final Map<Long, JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping
    public List<JournalEntry> journalEntryList() {
        return new ArrayList<>(journalEntryMap.values());
    }

    @PostMapping
    public boolean addJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntryMap.put(journalEntry.getId(), journalEntry);
        return true;
    }

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntry(@PathVariable Long id) {
        return journalEntryMap.get(id);
    }

    @DeleteMapping("id/{id}")
    public boolean deleteJournalEntry(@PathVariable Long id) {
        journalEntryMap.remove(id);
        return true;
    }
}
