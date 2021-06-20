package com.wild.corp.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "RECETTE")
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    private RecetteName recetteName;

    @OneToMany(mappedBy="recette")
    private Set<Etape> etapes;

    @Column(name = "PREPA")
    private Integer prepa;

    @Column(name = "CUISSON")
    private Integer cuisson;

    @Column(name = "POUR")
    private Integer pour;

    @Column(name = "POUR_TYPE")
    private Integer pourType;

    @Lob
    @Column(name = "IMAGE")
    private String image;

    @OneToMany(mappedBy="recette")
    private Set<IngredientUsed> ingredientsUsed;

    @ManyToMany
    private Set<User> stars;

    @ManyToOne
    private User user;

    public Recette() {

    }

    public Integer getPourType() {
        return pourType;
    }

    public void setPourType(Integer pourType) {
        this.pourType = pourType;
    }

    public Integer getCuisson() {
        return cuisson;
    }

    public void setCuisson(Integer cuisson) {
        this.cuisson = cuisson;
    }

    public Integer getPour() {
        return pour;
    }

    public void setPour(Integer pour) {
        this.pour = pour;
    }

    public Set<Etape> getEtapes() {
        return etapes;
    }

    public void setEtapes(Set<Etape> etapes) {
        this.etapes = etapes;
    }

    public Set<IngredientUsed> getIngredientsUsed() {
        return ingredientsUsed;
    }

    public void setIngredientsUsed(Set<IngredientUsed> ingredientsUsed) {
        this.ingredientsUsed = ingredientsUsed;
    }

    public Set<User> getStars() {
        return stars;
    }

    public void setStars(Set<User> stars) {
        this.stars = stars;
    }

    public Integer getPrepa() {
        return prepa;
    }

    public void setPrepa(Integer prepa) {
        this.prepa = prepa;
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

    public RecetteName getRecetteName() {
        return recetteName;
    }

    public void setRecetteName(RecetteName recetteName) {
        this.recetteName = recetteName;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recette)) return false;
        Recette recette = (Recette) o;
        return Objects.equals(getId(), recette.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
