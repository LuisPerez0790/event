package com.gob.scjn.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SitePage entity.
 */
public class SitePageDTO implements Serializable {

    private Long id;

    private Instant creationDate;

    private Instant updatedDate;

    private String menuEntry;

    private Long order;

    private Boolean status;

    private String slug;

    private Long siteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getMenuEntry() {
        return menuEntry;
    }

    public void setMenuEntry(String menuEntry) {
        this.menuEntry = menuEntry;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SitePageDTO sitePageDTO = (SitePageDTO) o;
        if (sitePageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sitePageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SitePageDTO{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", menuEntry='" + getMenuEntry() + "'" +
            ", order=" + getOrder() +
            ", status='" + isStatus() + "'" +
            ", slug='" + getSlug() + "'" +
            ", site=" + getSiteId() +
            "}";
    }
}
