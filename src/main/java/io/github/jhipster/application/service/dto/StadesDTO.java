package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Stades entity.
 */
public class StadesDTO implements Serializable {

    private Long id;

    @NotNull
    private String stade;

    @NotNull
    private String ville;

    private Integer capacite;

    private Double latitude;

    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StadesDTO stadesDTO = (StadesDTO) o;
        if(stadesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stadesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StadesDTO{" +
            "id=" + getId() +
            ", stade='" + getStade() + "'" +
            ", ville='" + getVille() + "'" +
            ", capacite=" + getCapacite() +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            "}";
    }
}
