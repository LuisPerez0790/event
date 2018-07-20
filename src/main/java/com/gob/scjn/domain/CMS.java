package com.gob.scjn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CMS.
 */
@Entity
@Table(name = "cms")
public class CMS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_id")
    private Long fileId;

    @ManyToOne
    @JsonIgnoreProperties("cms")
    private EventUser eventUser;

    @ManyToOne
    @JsonIgnoreProperties("cms")
    private Event event;

    @ManyToOne
    @JsonIgnoreProperties("cms")
    private Activity activity;

    @ManyToOne
    @JsonIgnoreProperties("cms")
    private SitePage sitePage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public CMS fileId(Long fileId) {
        this.fileId = fileId;
        return this;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public EventUser getEventUser() {
        return eventUser;
    }

    public CMS eventUser(EventUser eventUser) {
        this.eventUser = eventUser;
        return this;
    }

    public void setEventUser(EventUser eventUser) {
        this.eventUser = eventUser;
    }

    public Event getEvent() {
        return event;
    }

    public CMS event(Event event) {
        this.event = event;
        return this;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Activity getActivity() {
        return activity;
    }

    public CMS activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public SitePage getSitePage() {
        return sitePage;
    }

    public CMS sitePage(SitePage sitePage) {
        this.sitePage = sitePage;
        return this;
    }

    public void setSitePage(SitePage sitePage) {
        this.sitePage = sitePage;
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
        CMS cMS = (CMS) o;
        if (cMS.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cMS.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CMS{" +
            "id=" + getId() +
            ", fileId=" + getFileId() +
            "}";
    }
}
