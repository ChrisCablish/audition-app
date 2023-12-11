package com.example.auditionapp.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Auditionee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "AuditioneeStrengths",
            joinColumns = @JoinColumn(name = "auditionee_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private List<Attribute> strengths;

    @ManyToMany
    @JoinTable(
            name = "AuditioneeWeaknesses",
            joinColumns = @JoinColumn(name = "auditionee_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private List<Attribute> weaknesses;

    @ManyToMany
    @JoinTable(
            name = "AuditioneeNotes",
            joinColumns = @JoinColumn(name = "auditionee_id"),
            inverseJoinColumns = @JoinColumn(name = "noteentry_id")
    )
    private List<NoteEntry> notes;


    private int rating;

    public Auditionee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<Attribute> strengths) {
        this.strengths = strengths;
    }

    public List<Attribute> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<Attribute> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<NoteEntry> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntry> notes) {
        this.notes = notes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

