package com.gob.scjn.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * A Event.
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "start_date")
	private Instant startDate;

	@Column(name = "end_date")
	private Instant endDate;

	@Column(name = "url")
	private String url;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "acronym")
	private String acronym;

	@OneToOne(mappedBy = "event")
	private Venue venue;

	@OneToMany(mappedBy = "event")
	private Set<Activity> activities = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "event_id")
	private Set<EventLanguage> languages = new HashSet<>();

	@OneToMany(mappedBy = "event")
	private Set<CMS> cms = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "event_event_user", joinColumns = @JoinColumn(name = "events_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "event_users_id", referencedColumnName = "id"))
	private Set<EventUser> eventUsers = new HashSet<>();

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public Event startDate(Instant startDate) {
		this.startDate = startDate;
		return this;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public Event endDate(Instant endDate) {
		this.endDate = endDate;
		return this;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public String getUrl() {
		return url;
	}

	public Event url(String url) {
		this.url = url;
		return this;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Event imageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public Event enabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getAcronym() {
		return acronym;
	}

	public Event acronym(String acronym) {
		this.acronym = acronym;
		return this;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public Event activities(Set<Activity> activities) {
		this.activities = activities;
		return this;
	}

	public Event addActivity(Activity activity) {
		this.activities.add(activity);
		activity.setEvent(this);
		return this;
	}

	public Event removeActivity(Activity activity) {
		this.activities.remove(activity);
		activity.setEvent(null);
		return this;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Set<EventLanguage> getLanguages() {
		return languages;
	}

	public Event languages(Set<EventLanguage> eventLanguages) {
		this.languages = eventLanguages;
		return this;
	}

	// public Event addLanguages(EventLanguage eventLanguage) {
	// this.languages.add(eventLanguage);
	// eventLanguage.setEvent(this);
	// return this;
	// }

	public Event removeLanguages(EventLanguage eventLanguage) {
		this.languages.remove(eventLanguage);
		eventLanguage.setEvent(null);
		return this;
	}

	public void setLanguages(Set<EventLanguage> eventLanguages) {
		this.languages = eventLanguages;
	}

	public Set<CMS> getCms() {
		return cms;
	}

	public Event cms(Set<CMS> cMS) {
		this.cms = cMS;
		return this;
	}

	public Event addCms(CMS cMS) {
		this.cms.add(cMS);
		cMS.setEvent(this);
		return this;
	}

	public Event removeCms(CMS cMS) {
		this.cms.remove(cMS);
		cMS.setEvent(null);
		return this;
	}

	public void setCms(Set<CMS> cMS) {
		this.cms = cMS;
	}

	public Set<EventUser> getEventUsers() {
		return eventUsers;
	}

	public Event eventUsers(Set<EventUser> eventUsers) {
		this.eventUsers = eventUsers;
		return this;
	}

	public Event addEventUser(EventUser eventUser) {
		this.eventUsers.add(eventUser);
		eventUser.getSites().add(this);
		return this;
	}

	public Event removeEventUser(EventUser eventUser) {
		this.eventUsers.remove(eventUser);
		eventUser.getSites().remove(this);
		return this;
	}

	public void setEventUsers(Set<EventUser> eventUsers) {
		this.eventUsers = eventUsers;
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
		Event event = (Event) o;
		if (event.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), event.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", url=" + url + ", imageUrl="
				+ imageUrl + ", enabled=" + enabled + ", acronym=" + acronym + ", languages=" + languages
				+ ", eventUsers=" + eventUsers + "]";
	}

}
