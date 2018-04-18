package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Stades.
 */
@Entity
@Table(name = "stades")
public class Stades implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "stade", nullable = false)
    private String stade;

    @NotNull
    @Column(name = "ville", nullable = false)
    private String ville;

    @Column(name = "capacite")
    private Integer capacite;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @OneToMany(mappedBy = "stades")
    @JsonIgnore
    private Set<Matchs> idstades = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStade() {
        return stade;
    }

    public Stades stade(String stade) {
        this.stade = stade;
        return this;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public String getVille() {
        return ville;
    }

    public Stades ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public Stades capacite(Integer capacite) {
        this.capacite = capacite;
        return this;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Stades latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Stades longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Set<Matchs> getIdstades() {
        return idstades;
    }

    public Stades idstades(Set<Matchs> matchs) {
        this.idstades = matchs;
        return this;
    }

    public Stades addIdstade(Matchs matchs) {
        this.idstades.add(matchs);
        matchs.setStades(this);
        return this;
    }

    public Stades removeIdstade(Matchs matchs) {
        this.idstades.remove(matchs);
        matchs.setStades(null);
        return this;
    }

    public void setIdstades(Set<Matchs> matchs) {
        this.idstades = matchs;
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
        Stades stades = (Stades) o;
        if (stades.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stades.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Stades{" +
            "id=" + getId() +
            ", stade='" + getStade() + "'" +
            ", ville='" + getVille() + "'" +
            ", capacite=" + getCapacite() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            "}";
    }
}
