package com.gob.scjn.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.gob.scjn.domain.EventLanguage;

import java.util.Objects;

/**
 * A DTO for the Event entity.
 */
public class EventDTO implements Serializable {

    private Long id;

    private Instant startDate;

    private Instant endDate;

    private String url;

    private String imageUrl;

    private Boolean enabled;

    private String acronym;

    private Set<EventUserDTO> eventUsers = new HashSet<>();
    
    private Set<EventLanguage> languages = new HashSet<>();

    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Set<EventUserDTO> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(Set<EventUserDTO> eventUsers) {
        this.eventUsers = eventUsers;
    }
    
    public Set<EventLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<EventLanguage> languages) {
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

        EventDTO eventDTO = (EventDTO) o;
        if (eventDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "EventDTO [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", url=" + url
				+ ", imageUrl=" + imageUrl + ", enabled=" + enabled + ", acronym=" + acronym + ", eventUsers=" + eventUsers + ", languages=" + languages + "]";
	}

    
}
