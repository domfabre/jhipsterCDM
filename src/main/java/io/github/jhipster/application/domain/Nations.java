package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Groupes;

/**
 * A Nations.
 */
@Entity
@Table(name = "nations")
public class Nations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nation", nullable = false)
    private String nation;

    @Column(name = "confederation")
    private String confederation;

    @Column(name = "fifa")
    private Integer fifa;

    @Column(name = "cdm")
    private Integer cdm;

    @Enumerated(EnumType.STRING)
    @Column(name = "groupe")
    private Groupes groupe;

    @OneToMany(mappedBy = "nations")
    @JsonIgnore
    private Set<Resultats> idnations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public Nations nation(String nation) {
        this.nation = nation;
        return this;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getConfederation() {
        return confederation;
    }

    public Nations confederation(String confederation) {
        this.confederation = confederation;
        return this;
    }

    public void setConfederation(String confederation) {
        this.confederation = confederation;
    }

    public Integer getFifa() {
        return fifa;
    }

    public Nations fifa(Integer fifa) {
        this.fifa = fifa;
        return this;
    }

    public void setFifa(Integer fifa) {
        this.fifa = fifa;
    }

    public Integer getCdm() {
        return cdm;
    }

    public Nations cdm(Integer cdm) {
        this.cdm = cdm;
        return this;
    }

    public void setCdm(Integer cdm) {
        this.cdm = cdm;
    }

    public Groupes getGroupe() {
        return groupe;
    }

    public Nations groupe(Groupes groupe) {
        this.groupe = groupe;
        return this;
    }

    public void setGroupe(Groupes groupe) {
        this.groupe = groupe;
    }

    public Set<Resultats> getIdnations() {
        return idnations;
    }

    public Nations idnations(Set<Resultats> resultats) {
        this.idnations = resultats;
        return this;
    }

    public Nations addIdnation(Resultats resultats) {
        this.idnations.add(resultats);
        resultats.setNations(this);
        return this;
    }

    public Nations removeIdnation(Resultats resultats) {
        this.idnations.remove(resultats);
        resultats.setNations(null);
        return this;
    }

    public void setIdnations(Set<Resultats> resultats) {
        this.idnations = resultats;
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
        Nations nations = (Nations) o;
        if (nations.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nations.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Nations{" +
            "id=" + getId() +
            ", nation='" + getNation() + "'" +
            ", confederation='" + getConfederation() + "'" +
            ", fifa=" + getFifa() +
            ", cdm=" + getCdm() +
            ", groupe='" + getGroupe() + "'" +
            "}";
    }
}
