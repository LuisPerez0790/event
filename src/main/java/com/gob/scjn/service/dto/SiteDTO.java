package com.gob.scjn.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.gob.scjn.domain.enumeration.PageStatus;

/**
 * A DTO for the Site entity.
 */
public class SiteDTO implements Serializable {

    private Long id;

    private String acronym;

    private Instant date;

    private PageStatus status;

    private String title;

    @Lob
    private String subtitle;

    private Long paletteId;

    private Long footerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public PageStatus getStatus() {
        return status;
    }

    public void setStatus(PageStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Long getPaletteId() {
        return paletteId;
    }

    public void setPaletteId(Long siteColorPaletteId) {
        this.paletteId = siteColorPaletteId;
    }

    public Long getFooterId() {
        return footerId;
    }

    public void setFooterId(Long siteFooterId) {
        this.footerId = siteFooterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SiteDTO siteDTO = (SiteDTO) o;
        if (siteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), siteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SiteDTO{" +
            "id=" + getId() +
            ", acronym='" + getAcronym() + "'" +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", title='" + getTitle() + "'" +
            ", subtitle='" + getSubtitle() + "'" +
            ", palette=" + getPaletteId() +
            ", footer=" + getFooterId() +
            "}";
    }
}
