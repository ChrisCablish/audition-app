package com.example.auditionapp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Attribute {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "strengths")
    private List<Auditionee> auditioneesStrength;

    @ManyToMany(mappedBy = "weaknesses")
    private List<Auditionee> auditioneesWeakness;

    Attribute() {
    }

    public Attribute (String name) {
        this.name = name;
    }

    public String getName () {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
