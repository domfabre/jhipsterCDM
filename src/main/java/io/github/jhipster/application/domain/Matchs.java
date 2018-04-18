package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Phases;

/**
 * A Matchs.
 */
@Entity
@Table(name = "matchs")
public class Matchs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_match", nullable = false)
    private String match;

    @Column(name = "jhi_date")
    private String date;

    @Column(name = "heure")
    private String heure;

    @Column(name = "stade")
    private String stade;

    @Column(name = "domicile")
    private Boolean domicile;

    @Enumerated(EnumType.STRING)
    @Column(name = "phase")
    private Phases phase;

    @OneToMany(mappedBy = "matchs")
    @JsonIgnore
    private Set<Resultats> idmatches = new HashSet<>();

    @ManyToOne
    private Stades stades;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatch() {
        return match;
    }

    public Matchs match(String match) {
        this.match = match;
        return this;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getDate() {
        return date;
    }

    public Matchs date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public Matchs heure(String heure) {
        this.heure = heure;
        return this;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getStade() {
        return stade;
    }

    public Matchs stade(String stade) {
        this.stade = stade;
        return this;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public Boolean isDomicile() {
        return domicile;
    }

    public Matchs domicile(Boolean domicile) {
        this.domicile = domicile;
        return this;
    }

    public void setDomicile(Boolean domicile) {
        this.domicile = domicile;
    }

    public Phases getPhase() {
        return phase;
    }

    public Matchs phase(Phases phase) {
        this.phase = phase;
        return this;
    }

    public void setPhase(Phases phase) {
        this.phase = phase;
    }

    public Set<Resultats> getIdmatches() {
        return idmatches;
    }

    public Matchs idmatches(Set<Resultats> resultats) {
        this.idmatches = resultats;
        return this;
    }

    public Matchs addIdmatch(Resultats resultats) {
        this.idmatches.add(resultats);
        resultats.setMatchs(this);
        return this;
    }

    public Matchs removeIdmatch(Resultats resultats) {
        this.idmatches.remove(resultats);
        resultats.setMatchs(null);
        return this;
    }

    public void setIdmatches(Set<Resultats> resultats) {
        this.idmatches = resultats;
    }

    public Stades getStades() {
        return stades;
    }

    public Matchs stades(Stades stades) {
        this.stades = stades;
        return this;
    }

    public void setStades(Stades stades) {
        this.stades = stades;
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
        Matchs matchs = (Matchs) o;
        if (matchs.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matchs.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Matchs{" +
            "id=" + getId() +
            ", match='" + getMatch() + "'" +
            ", date='" + getDate() + "'" +
            ", heure='" + getHeure() + "'" +
            ", stade='" + getStade() + "'" +
            ", domicile='" + isDomicile() + "'" +
            ", phase='" + getPhase() + "'" +
            "}";
    }
}
