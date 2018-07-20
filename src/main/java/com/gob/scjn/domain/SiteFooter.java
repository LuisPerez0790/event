package com.gob.scjn.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SiteFooter.
 */
@Entity
@Table(name = "site_footer")
public class SiteFooter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "contact")
    private String contact;

    @Lob
    @Column(name = "links")
    private String links;

    @Lob
    @Column(name = "more_content")
    private String moreContent;

    @Column(name = "google_maps")
    private String googleMaps;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public SiteFooter address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public SiteFooter contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLinks() {
        return links;
    }

    public SiteFooter links(String links) {
        this.links = links;
        return this;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getMoreContent() {
        return moreContent;
    }

    public SiteFooter moreContent(String moreContent) {
        this.moreContent = moreContent;
        return this;
    }

    public void setMoreContent(String moreContent) {
        this.moreContent = moreContent;
    }

    public String getGoogleMaps() {
        return googleMaps;
    }

    public SiteFooter googleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
        return this;
    }

    public void setGoogleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
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
        SiteFooter siteFooter = (SiteFooter) o;
        if (siteFooter.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), siteFooter.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SiteFooter{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", contact='" + getContact() + "'" +
            ", links='" + getLinks() + "'" +
            ", moreContent='" + getMoreContent() + "'" +
            ", googleMaps='" + getGoogleMaps() + "'" +
            "}";
    }
}
