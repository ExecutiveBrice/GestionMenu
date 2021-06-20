package com.wild.corp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "INGREDIENT_USED")
public class IngredientUsed {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToOne
    private Ingredient ingredient;

    @Column(name = "QUANTITE")
    private Integer quantite;



    @Transient
    private boolean checked;

    @JsonIgnore
    @ManyToOne
    private Recette recette;

    @JsonIgnore
    @ManyToOne
    private Menu menu;

    public IngredientUsed() {

    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientUsed)) return false;
        IngredientUsed that = (IngredientUsed) o;
        return Objects.equals(getIngredient(), that.getIngredient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIngredient());
    }
}
