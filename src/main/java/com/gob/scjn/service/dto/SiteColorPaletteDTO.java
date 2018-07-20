package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SiteColorPalette entity.
 */
public class SiteColorPaletteDTO implements Serializable {

    private Long id;

    private String primary;

    private String secondary;

    private String inverse;

    private String complementary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getInverse() {
        return inverse;
    }

    public void setInverse(String inverse) {
        this.inverse = inverse;
    }

    public String getComplementary() {
        return complementary;
    }

    public void setComplementary(String complementary) {
        this.complementary = complementary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SiteColorPaletteDTO siteColorPaletteDTO = (SiteColorPaletteDTO) o;
        if (siteColorPaletteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), siteColorPaletteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SiteColorPaletteDTO{" +
            "id=" + getId() +
            ", primary='" + getPrimary() + "'" +
            ", secondary='" + getSecondary() + "'" +
            ", inverse='" + getInverse() + "'" +
            ", complementary='" + getComplementary() + "'" +
            "}";
    }
}
