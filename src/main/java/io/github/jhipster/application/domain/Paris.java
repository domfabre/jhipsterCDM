package io.github.jhipster.application.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Paris.
 */
@Entity
@Table(name = "paris")
public class Paris implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "but")
    private Integer but;

    @Column(name = "jocker")
    private Boolean jocker;

    @Column(name = "point")
    private Integer point;

    @ManyToOne
    private Joueurs joueurs;

    @OneToOne
    @JoinColumn(unique = true)
    private Resultats idparis;

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

    public Paris but(Integer but) {
        this.but = but;
        return this;
    }

    public void setBut(Integer but) {
        this.but = but;
    }

    public Boolean isJocker() {
        return jocker;
    }

    public Paris jocker(Boolean jocker) {
        this.jocker = jocker;
        return this;
    }

    public void setJocker(Boolean jocker) {
        this.jocker = jocker;
    }

    public Integer getPoint() {
        return point;
    }

    public Paris point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Joueurs getJoueurs() {
        return joueurs;
    }

    public Paris joueurs(Joueurs joueurs) {
        this.joueurs = joueurs;
        return this;
    }

    public void setJoueurs(Joueurs joueurs) {
        this.joueurs = joueurs;
    }

    public Resultats getIdparis() {
        return idparis;
    }

    public Paris idparis(Resultats resultats) {
        this.idparis = resultats;
        return this;
    }

    public void setIdparis(Resultats resultats) {
        this.idparis = resultats;
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
        Paris paris = (Paris) o;
        if (paris.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paris.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Paris{" +
            "id=" + getId() +
            ", but=" + getBut() +
            ", jocker='" + isJocker() + "'" +
            ", point=" + getPoint() +
            "}";
    }
}
