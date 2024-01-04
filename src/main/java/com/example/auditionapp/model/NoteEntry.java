package com.example.auditionapp.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class NoteEntry {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditionee_id")
    private Auditionee auditionee;

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
