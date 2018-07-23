package com.gob.scjn.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.gob.scjn.domain.ActivityLanguage;
import com.gob.scjn.domain.EventLanguage;

/**
 * A DTO for the Activity entity.
 */
public class ActivityDTO implements Serializable {

    private Long id;

    private Instant startDate;

    private Instant endDate;

    private String imageUrl;

    private Boolean enabled;

    private Long eventId;
    
    private Set<ActivityLanguage> languages = new HashSet<>();

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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    
    public Set<ActivityLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<ActivityLanguage> languages) {
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

        ActivityDTO activityDTO = (ActivityDTO) o;
        if (activityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "ActivityDTO [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", imageUrl=" + imageUrl
				+ ", enabled=" + enabled + ", eventId=" + eventId + ", languages=" + languages + "]";
	}

   
}
