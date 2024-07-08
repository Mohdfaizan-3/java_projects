package com.springbootedigest.journal.App.controller;

import com.springbootedigest.journal.App.entity.JournalEntry;
import com.springbootedigest.journal.App.entity.User;
import com.springbootedigest.journal.App.service.JournalEntryService;
import com.springbootedigest.journal.App.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/journals")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> journalEntryList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);

        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> addJournalEntry(@RequestBody JournalEntry journalEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User byUserName = userService.findByUserName(username);
        List<JournalEntry> journalEntryList= byUserName.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!journalEntryList.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return null;
    }

    @DeleteMapping("id{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(id, username);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry updatedJournal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User byUserName = userService.findByUserName(username);
        List<JournalEntry> journalEntryList= byUserName.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();

        if (!journalEntryList.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
            if (journalEntry.isPresent()) {
                JournalEntry oldJournal = journalEntry.get();
                oldJournal.setTitle(!updatedJournal.getTitle().equals("") ? updatedJournal.getTitle() : oldJournal.getTitle());
                oldJournal.setContent(updatedJournal.getContent() != null && !updatedJournal.getContent().equals("") ? updatedJournal.getContent() : oldJournal.getContent());
                journalEntryService.saveEntry(oldJournal);
                return new ResponseEntity<>(oldJournal, HttpStatus.FOUND);
            }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
