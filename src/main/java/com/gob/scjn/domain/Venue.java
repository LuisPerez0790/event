package com.gob.scjn.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Venue.
 */
@Entity
@Table(name = "venue")
public class Venue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "phone")
    private String phone;

    @Column(name = "url")
    private String url;

    @Column(name = "google_maps")
    private String googleMaps;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Venue name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Venue address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Venue imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public Venue phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public Venue url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGoogleMaps() {
        return googleMaps;
    }

    public Venue googleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
        return this;
    }

    public void setGoogleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
    }

    public String getDescription() {
        return description;
    }

    public Venue description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Venue venue = (Venue) o;
        if (venue.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), venue.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Venue{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", phone='" + getPhone() + "'" +
            ", url='" + getUrl() + "'" +
            ", googleMaps='" + getGoogleMaps() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
