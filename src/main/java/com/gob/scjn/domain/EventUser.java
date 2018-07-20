package com.gob.scjn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.gob.scjn.domain.enumeration.UserType;

/**
 * A EventUser.
 */
@Entity
@Table(name = "event_user")
public class EventUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "institution")
    private String institution;

    @Column(name = "position")
    private String position;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @OneToMany(mappedBy = "eventUser")
    private Set<CMS> cms = new HashSet<>();

    @ManyToMany(mappedBy = "eventUsers")
    @JsonIgnore
    private Set<Event> sites = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EventUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public EventUser lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public EventUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInstitution() {
        return institution;
    }

    public EventUser institution(String institution) {
        this.institution = institution;
        return this;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPosition() {
        return position;
    }

    public EventUser position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public EventUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public EventUser description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public EventUser imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserType getUserType() {
        return userType;
    }

    public EventUser userType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<CMS> getCms() {
        return cms;
    }

    public EventUser cms(Set<CMS> cMS) {
        this.cms = cMS;
        return this;
    }

    public EventUser addCms(CMS cMS) {
        this.cms.add(cMS);
        cMS.setEventUser(this);
        return this;
    }

    public EventUser removeCms(CMS cMS) {
        this.cms.remove(cMS);
        cMS.setEventUser(null);
        return this;
    }

    public void setCms(Set<CMS> cMS) {
        this.cms = cMS;
    }

    public Set<Event> getSites() {
        return sites;
    }

    public EventUser sites(Set<Event> events) {
        this.sites = events;
        return this;
    }

    public EventUser addSite(Event event) {
        this.sites.add(event);
        event.getEventUsers().add(this);
        return this;
    }

    public EventUser removeSite(Event event) {
        this.sites.remove(event);
        event.getEventUsers().remove(this);
        return this;
    }

    public void setSites(Set<Event> events) {
        this.sites = events;
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
        EventUser eventUser = (EventUser) o;
        if (eventUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventUser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", institution='" + getInstitution() + "'" +
            ", position='" + getPosition() + "'" +
            ", email='" + getEmail() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", userType='" + getUserType() + "'" +
            "}";
    }
}
