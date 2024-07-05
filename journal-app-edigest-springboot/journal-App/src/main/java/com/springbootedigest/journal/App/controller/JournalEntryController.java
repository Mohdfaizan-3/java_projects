package com.springbootedigest.journal.App.controller;

import com.springbootedigest.journal.App.entity.JournalEntry;
import com.springbootedigest.journal.App.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journals")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> journalEntryList() {
        return journalEntryService.findAll();
    }

    @PostMapping
    public boolean addJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return true;
    }

    @GetMapping("id/{id}")
    public Optional<JournalEntry> getJournalEntry(@PathVariable ObjectId id) {
     return Optional.ofNullable(journalEntryService.findById(id).orElse(null));
    }

    @DeleteMapping("id/{id}")
    public void deleteJournalEntry(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry updatedJournal) {
        JournalEntry oldJournal = journalEntryService.findById(id).orElse(null);
        if (oldJournal != null) {
            oldJournal.setTitle(updatedJournal.getTitle() != null && !updatedJournal.getTitle().equals("") ? updatedJournal.getTitle() : oldJournal.getTitle());
            oldJournal.setContent(updatedJournal.getContent() != null && !updatedJournal.getContent().equals("") ? updatedJournal.getContent() : oldJournal.getContent());
//            oldJournal.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(oldJournal);
        }

        return oldJournal;
    }
}
