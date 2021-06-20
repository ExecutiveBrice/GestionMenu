package com.wild.corp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "JOUR")
public class Jour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOM_JOUR")
    private String nomJour;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="midi")
    private Set<RecetteUsed> recettesmidi;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="soir")
    private Set<RecetteUsed> recettessoir;

    @JsonIgnore
    @ManyToOne
    private Menu menu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomJour() {
        return nomJour;
    }

    public void setNomJour(String nomJour) {
        this.nomJour = nomJour;
    }

    public Set<RecetteUsed> getRecettesmidi() {
        return recettesmidi;
    }

    public void setRecettesmidi(Set<RecetteUsed> recettesmidi) {
        this.recettesmidi = recettesmidi;
    }

    public Set<RecetteUsed> getRecettessoir() {
        return recettessoir;
    }

    public void setRecettessoir(Set<RecetteUsed> recettessoir) {
        this.recettessoir = recettessoir;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
