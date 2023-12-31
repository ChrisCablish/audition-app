package com.example.auditionapp.model;
import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="auditionee_id")
    private Auditionee auditionee;

    public Image() {
    }
    public Image(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAuditionee(Auditionee auditionee) {
        this.auditionee = auditionee;
    }
}
