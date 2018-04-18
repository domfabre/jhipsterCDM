package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Resultats entity.
 */
public class ResultatsDTO implements Serializable {

    private Long id;

    private Integer but;

    private Integer tab;

    private Integer jaune;

    private Integer rouge;

    private Boolean domicile;

    private Long nationsId;

    private Long matchsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBut() {
        return but;
    }

    public void setBut(Integer but) {
        this.but = but;
    }

    public Integer getTab() {
        return tab;
    }

    public void setTab(Integer tab) {
        this.tab = tab;
    }

    public Integer getJaune() {
        return jaune;
    }

    public void setJaune(Integer jaune) {
        this.jaune = jaune;
    }

    public Integer getRouge() {
        return rouge;
    }

    public void setRouge(Integer rouge) {
        this.rouge = rouge;
    }

    public Boolean isDomicile() {
        return domicile;
    }

    public void setDomicile(Boolean domicile) {
        this.domicile = domicile;
    }

    public Long getNationsId() {
        return nationsId;
    }

    public void setNationsId(Long nationsId) {
        this.nationsId = nationsId;
    }

    public Long getMatchsId() {
        return matchsId;
    }

    public void setMatchsId(Long matchsId) {
        this.matchsId = matchsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResultatsDTO resultatsDTO = (ResultatsDTO) o;
        if(resultatsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultatsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResultatsDTO{" +
            "id=" + getId() +
            ", but=" + getBut() +
            ", tab=" + getTab() +
            ", jaune=" + getJaune() +
            ", rouge=" + getRouge() +
            ", domicile='" + isDomicile() + "'" +
            "}";
    }
}
