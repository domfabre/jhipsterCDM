package io.github.jhipster.application.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Resultats.
 */
@Entity
@Table(name = "resultats")
public class Resultats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "but")
    private Integer but;

    @Column(name = "tab")
    private Integer tab;

    @Column(name = "jaune")
    private Integer jaune;

    @Column(name = "rouge")
    private Integer rouge;

    @Column(name = "domicile")
    private Boolean domicile;

    @ManyToOne
    private Nations nations;

    @ManyToOne
    private Matchs matchs;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBut() {
        return but;
    }

    public Resultats but(Integer but) {
        this.but = but;
        return this;
    }

    public void setBut(Integer but) {
        this.but = but;
    }

    public Integer getTab() {
        return tab;
    }

    public Resultats tab(Integer tab) {
        this.tab = tab;
        return this;
    }

    public void setTab(Integer tab) {
        this.tab = tab;
    }

    public Integer getJaune() {
        return jaune;
    }

    public Resultats jaune(Integer jaune) {
        this.jaune = jaune;
        return this;
    }

    public void setJaune(Integer jaune) {
        this.jaune = jaune;
    }

    public Integer getRouge() {
        return rouge;
    }

    public Resultats rouge(Integer rouge) {
        this.rouge = rouge;
        return this;
    }

    public void setRouge(Integer rouge) {
        this.rouge = rouge;
    }

    public Boolean isDomicile() {
        return domicile;
    }

    public Resultats domicile(Boolean domicile) {
        this.domicile = domicile;
        return this;
    }

    public void setDomicile(Boolean domicile) {
        this.domicile = domicile;
    }

    public Nations getNations() {
        return nations;
    }

    public Resultats nations(Nations nations) {
        this.nations = nations;
        return this;
    }

    public void setNations(Nations nations) {
        this.nations = nations;
    }

    public Matchs getMatchs() {
        return matchs;
    }

    public Resultats matchs(Matchs matchs) {
        this.matchs = matchs;
        return this;
    }

    public void setMatchs(Matchs matchs) {
        this.matchs = matchs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resultats resultats = (Resultats) o;
        if (resultats.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultats.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Resultats{" +
            "id=" + getId() +
            ", but=" + getBut() +
            ", tab=" + getTab() +
            ", jaune=" + getJaune() +
            ", rouge=" + getRouge() +
            ", domicile='" + isDomicile() + "'" +
            "}";
    }
}
