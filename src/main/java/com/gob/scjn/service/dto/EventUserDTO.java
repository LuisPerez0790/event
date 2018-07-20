package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.gob.scjn.domain.enumeration.UserType;

/**
 * A DTO for the EventUser entity.
 */
public class EventUserDTO implements Serializable {

    private Long id;

    private String name;

    private String lastName;

    private String userName;

    private String institution;

    private String position;

    private String email;

    private String description;

    private String imageUrl;

    private UserType userType;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventUserDTO eventUserDTO = (EventUserDTO) o;
        if (eventUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventUserDTO{" +
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
