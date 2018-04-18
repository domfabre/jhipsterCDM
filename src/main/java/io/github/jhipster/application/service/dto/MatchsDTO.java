package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.Phases;

/**
 * A DTO for the Matchs entity.
 */
public class MatchsDTO implements Serializable {

    private Long id;

    @NotNull
    private String match;

    private String date;

    private String heure;

    private String stade;

    private Boolean domicile;

    private Phases phase;

    private Long stadesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public Boolean isDomicile() {
        return domicile;
    }

    public void setDomicile(Boolean domicile) {
        this.domicile = domicile;
    }

    public Phases getPhase() {
        return phase;
    }

    public void setPhase(Phases phase) {
        this.phase = phase;
    }

    public Long getStadesId() {
        return stadesId;
    }

    public void setStadesId(Long stadesId) {
        this.stadesId = stadesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MatchsDTO matchsDTO = (MatchsDTO) o;
        if(matchsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matchsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MatchsDTO{" +
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
