package com.example.auditionapp.repository;

import com.example.auditionapp.model.NoteEntry;
import org.springframework.data.repository.CrudRepository;

public interface NoteEntryRepository extends CrudRepository<NoteEntry, Long> {
}
