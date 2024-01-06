package com.example.auditionapp.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Auditionee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "auditionee_strengths",
            joinColumns = @JoinColumn(name = "auditionee_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private List<Attribute> strengths;

    @ManyToMany
    @JoinTable(
            name = "auditionee_weaknesses",
            joinColumns = @JoinColumn(name = "auditionee_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private List<Attribute> weaknesses;

    @OneToMany(mappedBy = "auditionee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoteEntry> notes = new ArrayList<>();

    @OneToOne(mappedBy = "auditionee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Image image;

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

    public void setImage(Image image) {
        this.image = image;
    }
}

