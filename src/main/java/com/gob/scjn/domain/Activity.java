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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Activity.
 */
@Entity
@Table(name = "activity")
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "start_date")
	private Instant startDate;

	@Column(name = "end_date")
	private Instant endDate;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "enabled")
	private Boolean enabled;

	@ManyToOne
	@JsonIgnoreProperties("activities")
	private Event event;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "activity_id")
	private Set<ActivityLanguage> languages = new HashSet<>();

	@OneToMany(mappedBy = "activity")
	private Set<CMS> cms = new HashSet<>();

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

	public Activity startDate(Instant startDate) {
		this.startDate = startDate;
		return this;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public Activity endDate(Instant endDate) {
		this.endDate = endDate;
		return this;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Activity imageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public Activity enabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Event getEvent() {
		return event;
	}

	public Activity event(Event event) {
		this.event = event;
		return this;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Set<ActivityLanguage> getLanguages() {
		return languages;
	}

	public Activity languages(Set<ActivityLanguage> activityLanguages) {
		this.languages = activityLanguages;
		return this;
	}

	// public Activity addLanguages(ActivityLanguage activityLanguage) {
	// this.languages.add(activityLanguage);
	// activityLanguage.setActivity(this);
	// return this;
	// }

	public Activity removeLanguages(ActivityLanguage activityLanguage) {
		this.languages.remove(activityLanguage);
		activityLanguage.setActivity(null);
		return this;
	}

	public void setLanguages(Set<ActivityLanguage> activityLanguages) {
		this.languages = activityLanguages;
	}

	public Set<CMS> getCms() {
		return cms;
	}

	public Activity cms(Set<CMS> cMS) {
		this.cms = cMS;
		return this;
	}

	public Activity addCms(CMS cMS) {
		this.cms.add(cMS);
		cMS.setActivity(this);
		return this;
	}

	public Activity removeCms(CMS cMS) {
		this.cms.remove(cMS);
		cMS.setActivity(null);
		return this;
	}

	public void setCms(Set<CMS> cMS) {
		this.cms = cMS;
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
		Activity activity = (Activity) o;
		if (activity.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), activity.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", imageUrl=" + imageUrl
				+ ", enabled=" + enabled + ", event=" + event + ", languages=" + languages + ", cms=" + cms + "]";
	}

}
