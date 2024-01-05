package com.example.auditionapp.service;

import com.example.auditionapp.model.NoteEntry;
import com.example.auditionapp.repository.NoteEntryRepository;
import org.springframework.stereotype.Service;


@Service
public class NoteEntryService {
    private final NoteEntryRepository noteEntryRepository;

    public NoteEntryService(NoteEntryRepository noteEntryRepository) {
        this.noteEntryRepository = noteEntryRepository;
    }

    public void addNote (NoteEntry noteEntry) {
        noteEntryRepository.save(noteEntry);
    }
}
