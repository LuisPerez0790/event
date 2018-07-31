package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.gob.scjn.domain.enumeration.Language;

/**
 * A DTO for the EventLanguage entity.
 */

public class EventLanguageDTO implements Serializable {

	private Long id;

	private String name;

	private String description;

	private String address;

	private Language language;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		EventLanguageDTO eventLanguageDTO = (EventLanguageDTO) o;
		if (eventLanguageDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), eventLanguageDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "EventLanguageDTO [id=" + id + ", name=" + name + ", description=" + description + ", address=" + address
				+ ", language=" + language + "]";
	}

}
