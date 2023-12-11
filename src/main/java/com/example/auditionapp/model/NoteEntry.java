package com.example.auditionapp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class NoteEntry {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;
    private String text;

    @ManyToMany(mappedBy = "notes")
    private List<Auditionee> auditionees;

    public NoteEntry() {
        this.date = LocalDateTime.now();
    }

    public NoteEntry(LocalDateTime date, String text) {
        this.date = LocalDateTime.now();
        this.text = text;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
