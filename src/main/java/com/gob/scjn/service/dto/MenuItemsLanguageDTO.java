package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.gob.scjn.domain.enumeration.Language;

/**
 * A DTO for the MenuItemsLanguage entity.
 */
public class MenuItemsLanguageDTO implements Serializable {

    private Long id;

    private String name;

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

        MenuItemsLanguageDTO menuItemsLanguageDTO = (MenuItemsLanguageDTO) o;
        if (menuItemsLanguageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuItemsLanguageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuItemsLanguageDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", language='" + getLanguage() + "'" +
            "}";
    }
}
