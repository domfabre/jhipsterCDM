package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Joueurs.
 */
@Entity
@Table(name = "joueurs")
public class Joueurs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "joueur", nullable = false)
    private String joueur;

    @Column(name = "courriel")
    private String courriel;

    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "joueurs")
    @JsonIgnore
    private Set<Paris> idjoueurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoueur() {
        return joueur;
    }

    public Joueurs joueur(String joueur) {
        this.joueur = joueur;
        return this;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public String getCourriel() {
        return courriel;
    }

    public Joueurs courriel(String courriel) {
        this.courriel = courriel;
        return this;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getAvatar() {
        return avatar;
    }

    public Joueurs avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Paris> getIdjoueurs() {
        return idjoueurs;
    }

    public Joueurs idjoueurs(Set<Paris> parises) {
        this.idjoueurs = parises;
        return this;
    }

    public Joueurs addIdjoueur(Paris paris) {
        this.idjoueurs.add(paris);
        paris.setJoueurs(this);
        return this;
    }

    public Joueurs removeIdjoueur(Paris paris) {
        this.idjoueurs.remove(paris);
        paris.setJoueurs(null);
        return this;
    }

    public void setIdjoueurs(Set<Paris> parises) {
        this.idjoueurs = parises;
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
        Joueurs joueurs = (Joueurs) o;
        if (joueurs.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), joueurs.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Joueurs{" +
            "id=" + getId() +
            ", joueur='" + getJoueur() + "'" +
            ", courriel='" + getCourriel() + "'" +
            ", avatar='" + getAvatar() + "'" +
            "}";
    }
}
