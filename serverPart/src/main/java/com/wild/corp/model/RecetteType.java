package com.wild.corp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "RECETTE_TYPE")
public class RecetteType {
    @Id
    @Column(name = "Name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="type")
    private List<RecetteName> recettesName;

    @Column(name = "ORDRE", nullable = false)
    private Integer ordre;

    public RecetteType() {

    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecetteName> getRecettesName() {
        return recettesName;
    }

    public void setRecettesName(List<RecetteName> recettesName) {
        this.recettesName = recettesName;
    }
}
