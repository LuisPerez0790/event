package com.gob.scjn.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SiteColorPalette.
 */
@Entity
@Table(name = "site_color_palette")
public class SiteColorPalette implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_primary")
    private String primary;

    @Column(name = "secondary")
    private String secondary;

    @Column(name = "inverse")
    private String inverse;

    @Column(name = "complementary")
    private String complementary;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimary() {
        return primary;
    }

    public SiteColorPalette primary(String primary) {
        this.primary = primary;
        return this;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public SiteColorPalette secondary(String secondary) {
        this.secondary = secondary;
        return this;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getInverse() {
        return inverse;
    }

    public SiteColorPalette inverse(String inverse) {
        this.inverse = inverse;
        return this;
    }

    public void setInverse(String inverse) {
        this.inverse = inverse;
    }

    public String getComplementary() {
        return complementary;
    }

    public SiteColorPalette complementary(String complementary) {
        this.complementary = complementary;
        return this;
    }

    public void setComplementary(String complementary) {
        this.complementary = complementary;
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
        SiteColorPalette siteColorPalette = (SiteColorPalette) o;
        if (siteColorPalette.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), siteColorPalette.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SiteColorPalette{" +
            "id=" + getId() +
            ", primary='" + getPrimary() + "'" +
            ", secondary='" + getSecondary() + "'" +
            ", inverse='" + getInverse() + "'" +
            ", complementary='" + getComplementary() + "'" +
            "}";
    }
}
