package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Joueurs entity.
 */
public class JoueursDTO implements Serializable {

    private Long id;

    @NotNull
    private String joueur;

    private String courriel;

    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JoueursDTO joueursDTO = (JoueursDTO) o;
        if(joueursDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), joueursDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JoueursDTO{" +
            "id=" + getId() +
            ", joueur='" + getJoueur() + "'" +
            ", courriel='" + getCourriel() + "'" +
            ", avatar='" + getAvatar() + "'" +
            "}";
    }
}
