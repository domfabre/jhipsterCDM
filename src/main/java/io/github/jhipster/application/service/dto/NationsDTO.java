package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.Groupes;

/**
 * A DTO for the Nations entity.
 */
public class NationsDTO implements Serializable {

    private Long id;

    @NotNull
    private String nation;

    private String confederation;

    private Integer fifa;

    private Integer cdm;

    private Groupes groupe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getConfederation() {
        return confederation;
    }

    public void setConfederation(String confederation) {
        this.confederation = confederation;
    }

    public Integer getFifa() {
        return fifa;
    }

    public void setFifa(Integer fifa) {
        this.fifa = fifa;
    }

    public Integer getCdm() {
        return cdm;
    }

    public void setCdm(Integer cdm) {
        this.cdm = cdm;
    }

    public Groupes getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupes groupe) {
        this.groupe = groupe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NationsDTO nationsDTO = (NationsDTO) o;
        if(nationsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nationsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NationsDTO{" +
            "id=" + getId() +
            ", nation='" + getNation() + "'" +
            ", confederation='" + getConfederation() + "'" +
            ", fifa=" + getFifa() +
            ", cdm=" + getCdm() +
            ", groupe='" + getGroupe() + "'" +
            "}";
    }
}
