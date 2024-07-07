package com.springbootedigest.journal.App.controller;

import com.springbootedigest.journal.App.entity.JournalEntry;
import com.springbootedigest.journal.App.entity.User;
import com.springbootedigest.journal.App.service.JournalEntryService;
import com.springbootedigest.journal.App.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journals")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> journalEntryList(@PathVariable String username) {
        User user = userService.findByUserName(username);

        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> addJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable String username) {
        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{username}/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id, @PathVariable String username) {
        journalEntryService.deleteById(id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{username}/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry updatedJournal, @PathVariable String username) {

        JournalEntry oldJournal = journalEntryService.findById(id).orElse(null);

        if (oldJournal != null) {
            oldJournal.setTitle(!updatedJournal.getTitle().equals("") ? updatedJournal.getTitle() : oldJournal.getTitle());
            oldJournal.setContent(updatedJournal.getContent() != null && !updatedJournal.getContent().equals("") ? updatedJournal.getContent() : oldJournal.getContent());
            journalEntryService.saveEntry(oldJournal);
            return new ResponseEntity<>(oldJournal, HttpStatus.FOUND);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
