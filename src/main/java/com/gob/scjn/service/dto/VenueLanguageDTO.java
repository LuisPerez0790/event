package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.gob.scjn.domain.enumeration.Language;

/**
 * A DTO for the VenueLanguage entity.
 */
public class VenueLanguageDTO implements Serializable {

    private Long id;

    private String name;

    private String address;

    private String description;

    private Language language;

    private Long venueId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getVenueId() {
		return venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VenueLanguageDTO venueLanguageDTO = (VenueLanguageDTO) o;
        if (venueLanguageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), venueLanguageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "VenueLanguageDTO [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description
				+ ", language=" + language + ", venueId=" + venueId + "]";
	}

	
    
}
