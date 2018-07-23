package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MenuItems entity.
 */
public class MenuItemsDTO implements Serializable {

    private Long id;

    private Integer weight;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuItemsDTO menuItemsDTO = (MenuItemsDTO) o;
        if (menuItemsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuItemsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuItemsDTO{" +
            "id=" + getId() +
            ", weight=" + getWeight() +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
