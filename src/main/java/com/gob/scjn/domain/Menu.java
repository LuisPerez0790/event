package com.gob.scjn.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A Menu.
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "menu")
    private Set<MenuItems> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MenuItems> getItems() {
        return items;
    }

    public Menu items(Set<MenuItems> menuItems) {
        this.items = menuItems;
        return this;
    }

    public Menu addItems(MenuItems menuItems) {
        this.items.add(menuItems);
        menuItems.setMenu(this);
        return this;
    }

    public Menu removeItems(MenuItems menuItems) {
        this.items.remove(menuItems);
        menuItems.setMenu(null);
        return this;
    }

    public void setItems(Set<MenuItems> menuItems) {
        this.items = menuItems;
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
        Menu menu = (Menu) o;
        if (menu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "Menu [id=" + id + ", items=" + items + "]";
	}

    
}
