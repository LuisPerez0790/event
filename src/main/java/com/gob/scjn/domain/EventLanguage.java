package com.gob.scjn.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gob.scjn.domain.enumeration.Language;

/**
 * A EventLanguage.
 */
@Entity
@Table(name = "event_language")
public class EventLanguage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(name = "language")
	private Language language;

	@Column(name = "event_id")
	private Long event;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public EventLanguage name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public EventLanguage description(String description) {
		this.description = description;
		return this;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public EventLanguage address(String address) {
		this.address = address;
		return this;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Language getLanguage() {
		return language;
	}

	public EventLanguage language(Language language) {
		this.language = language;
		return this;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Long getEvent() {
		return event;
	}

	public EventLanguage event(Long event) {
		this.event = event;
		return this;
	}

	public void setEvent(Long event) {
		this.event = event;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EventLanguage eventLanguage = (EventLanguage) o;
		if (eventLanguage.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), eventLanguage.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "EventLanguage{" + "id=" + getId() + ", name='" + getName() + "'" + ", description='" + getDescription()
				+ "'" + ", address='" + getAddress() + "'" + ", language='" + getLanguage() + "'" + "}";
	}
}
