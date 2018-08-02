package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Venue entity.
 */
public class VenueDTO implements Serializable {

	private Long id;

	private String imageUrl;

	private String phone;

	private String url;

	private String googleMaps;

	private Long eventId;

	private Set<VenueLanguageDTO> languages = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	public Set<VenueLanguageDTO> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<VenueLanguageDTO> languages) {
		this.languages = languages;
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
		return "VenueDTO [id=" + id + ", imageUrl=" + imageUrl + ", phone=" + phone + ", url=" + url + ", googleMaps="
				+ googleMaps + ", eventId=" + eventId + ", languages=" + languages + "]";
	}

}
