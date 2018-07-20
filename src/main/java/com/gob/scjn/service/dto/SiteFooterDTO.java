package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the SiteFooter entity.
 */
public class SiteFooterDTO implements Serializable {

    private Long id;

    @Lob
    private String address;

    @Lob
    private String contact;

    @Lob
    private String links;

    @Lob
    private String moreContent;

    private String googleMaps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getMoreContent() {
        return moreContent;
    }

    public void setMoreContent(String moreContent) {
        this.moreContent = moreContent;
    }

    public String getGoogleMaps() {
        return googleMaps;
    }

    public void setGoogleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SiteFooterDTO siteFooterDTO = (SiteFooterDTO) o;
        if (siteFooterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), siteFooterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SiteFooterDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", contact='" + getContact() + "'" +
            ", links='" + getLinks() + "'" +
            ", moreContent='" + getMoreContent() + "'" +
            ", googleMaps='" + getGoogleMaps() + "'" +
            "}";
    }
}
