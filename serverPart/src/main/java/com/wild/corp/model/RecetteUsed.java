package com.wild.corp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "RECETTE_USED")
public class RecetteUsed {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToOne
    private Recette recette;

    @Column(name = "QUANTITE")
    private Integer quantite;

    @JsonIgnore
    @ManyToOne
    private Jour midi;

    @JsonIgnore
    @ManyToOne
    private Jour soir;

    public RecetteUsed() {
    }

    public RecetteUsed(Recette recette, Integer quantite) {
        this.recette = recette;
        this.quantite = quantite;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Jour getMidi() {
        return midi;
    }

    public void setMidi(Jour midi) {
        this.midi = midi;
    }

    public Jour getSoir() {
        return soir;
    }

    public void setSoir(Jour soir) {
        this.soir = soir;
    }
}
