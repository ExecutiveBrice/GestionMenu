package com.wild.corp.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "INGREDIENT")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOM", nullable = false)
    private String nom;

    @Transient
    private boolean state;

    @Transient
    private boolean lock;

    @Column(name = "UNITE")
    private String unite;

    @Column(name = "COEF")
    private Integer coef;

    @Column(name = "UNITE_VENTE")
    private String uniteVente;

    @Column(name = "RAYON")
    private String rayon;

    @Column(name = "PROTEINES")
    private String proteines;

    @Column(name = "GLUCIDES")
    private String glucides ;

    @Column(name = "LIPIDES")
    private String lipides ;

    @Column(name = "SUCRES")
    private String sucres ;

    @Column(name = "ACIDE_GRAS")
    private String acidegras;

    @Column(name = "SEL")
    private String sel;

    @Column(name = "KJOULE")
    private String kjoule;

    @Column(name = "KCAL")
    private String kcal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getCoef() {
        return coef;
    }

    public void setCoef(Integer coef) {
        this.coef = coef;
    }

    public String getUniteVente() {
        return uniteVente;
    }

    public void setUniteVente(String uniteVente) {
        this.uniteVente = uniteVente;
    }

    public String getProteines() {
        return proteines;
    }

    public void setProteines(String proteines) {
        this.proteines = proteines;
    }

    public String getGlucides() {
        return glucides;
    }

    public void setGlucides(String glucides) {
        this.glucides = glucides;
    }

    public String getLipides() {
        return lipides;
    }

    public void setLipides(String lipides) {
        this.lipides = lipides;
    }

    public String getSucres() {
        return sucres;
    }

    public void setSucres(String sucres) {
        this.sucres = sucres;
    }

    public String getAcidegras() {
        return acidegras;
    }

    public void setAcidegras(String acidegras) {
        this.acidegras = acidegras;
    }

    public String getSel() {
        return sel;
    }

    public void setSel(String sel) {
        this.sel = sel;
    }

    public String getKjoule() {
        return kjoule;
    }

    public void setKjoule(String kjoule) {
        this.kjoule = kjoule;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
