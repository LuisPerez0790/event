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

import com.gob.scjn.domain.enumeration.Language;

/**
 * A MenuItemsLanguage.
 */
@Entity
@Table(name = "menu_items_language")
public class MenuItemsLanguage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name="menu_item_id")
    private Long item;

	@Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;
    
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

    public MenuItemsLanguage name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public MenuItemsLanguage language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    
    public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
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
        MenuItemsLanguage menuItemsLanguage = (MenuItemsLanguage) o;
        if (menuItemsLanguage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuItemsLanguage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "MenuItemsLanguage [id=" + id + ", name=" + name + ", item=" + item + ", language=" + language + "]";
	}

	
   
}
