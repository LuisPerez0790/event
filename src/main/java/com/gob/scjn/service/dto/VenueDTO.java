package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Venue entity.
 */
public class VenueDTO implements Serializable {

    private Long id;

    private String name;

    private String address;

    private String imageUrl;

    private String phone;

    private String url;

    private String googleMaps;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGoogleMaps() {
        return googleMaps;
    }

    public void setGoogleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VenueDTO venueDTO = (VenueDTO) o;
        if (venueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), venueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VenueDTO{" +
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
