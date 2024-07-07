package com.springbootedigest.journal.App.service;

import com.springbootedigest.journal.App.Repository.JournalEntryRepository;
import com.springbootedigest.journal.App.entity.JournalEntry;
import com.springbootedigest.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        User user = userService.findByUserName(username);

        JournalEntry save = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(save);
        userService.saveEntry(user);
    }

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String username) {
        User user = userService.findByUserName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

    public void saveEntry(JournalEntry oldJournal) {
        journalEntryRepository.save(oldJournal);
    }
}
