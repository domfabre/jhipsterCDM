package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Paris entity.
 */
public class ParisDTO implements Serializable {

    private Long id;

    private Integer but;

    private Boolean jocker;

    private Integer point;

    private Long joueursId;

    private Long idparisId;

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

    public Boolean isJocker() {
        return jocker;
    }

    public void setJocker(Boolean jocker) {
        this.jocker = jocker;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Long getJoueursId() {
        return joueursId;
    }

    public void setJoueursId(Long joueursId) {
        this.joueursId = joueursId;
    }

    public Long getIdparisId() {
        return idparisId;
    }

    public void setIdparisId(Long resultatsId) {
        this.idparisId = resultatsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParisDTO parisDTO = (ParisDTO) o;
        if(parisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), parisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParisDTO{" +
            "id=" + getId() +
            ", but=" + getBut() +
            ", jocker='" + isJocker() + "'" +
            ", point=" + getPoint() +
            "}";
    }
}
