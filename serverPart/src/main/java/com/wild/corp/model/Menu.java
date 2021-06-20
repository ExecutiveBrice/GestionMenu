package com.wild.corp.model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NUMERO_SEMAINE")
    private Integer numeroSemaine;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="menu" , orphanRemoval=true)
    private Set<Jour> semaine;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="menu")
    private Set<IngredientUsed> ingredientsUsed;

    @ManyToOne
    private User user;

    void Menu(){
        ingredientsUsed = new HashSet<>();
        semaine = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroSemaine() {
        return numeroSemaine;
    }

    public void setNumeroSemaine(Integer numeroSemaine) {
        this.numeroSemaine = numeroSemaine;
    }

    public Set<Jour> getSemaine() {
        return semaine;
    }

    public void setSemaine(Set<Jour> semaine) {
        this.semaine = semaine;
    }

    public Set<IngredientUsed> getIngredientsUsed() {
        return ingredientsUsed;
    }

    public void setIngredientsUsed(Set<IngredientUsed> ingredientUsed) {
        this.ingredientsUsed = ingredientsUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
