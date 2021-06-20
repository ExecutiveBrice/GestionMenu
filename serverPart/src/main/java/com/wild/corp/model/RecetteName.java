package com.wild.corp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "RECETTE_NAME")
public class RecetteName {
    @Id
    @Column(name = "NOM", nullable = false)
    private String nom;

    @JsonIgnore
    @OneToMany(mappedBy="recetteName")
    private List<Recette> recettes;

    @Transient
    private boolean state;

    @ManyToOne
    private RecetteType type;

    public RecetteName() {

    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public RecetteType getType() {
        return type;
    }

    public void setType(RecetteType type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
    }
}
