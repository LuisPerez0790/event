package com.gob.scjn.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A MenuItems.
 */
@Entity
@Table(name = "menu_items")
public class MenuItems implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "weight")
	private Integer weight;

	@Column(name = "url")
	private String url;
	
	@Column(name= "menu_id")
	private Long menu;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_item_id")
	private Set<MenuItemsLanguage> languages = new HashSet<>();


	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWeight() {
		return weight;
	}

	public MenuItems weight(Integer weight) {
		this.weight = weight;
		return this;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getUrl() {
		return url;
	}

	public MenuItems url(String url) {
		this.url = url;
		return this;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getMenu() {
		return menu;
	}

	public void setMenu(Long menu) {
		this.menu = menu;
	}

	public Set<MenuItemsLanguage> getLanguages() {
		return languages;
	}

	public MenuItems languages(Set<MenuItemsLanguage> menuItemsLanguages) {
		this.languages = menuItemsLanguages;
		return this;
	}

	public void setLanguages(Set<MenuItemsLanguage> menuItemsLanguages) {
		this.languages = menuItemsLanguages;
	}

	
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters
	// and setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MenuItems menuItems = (MenuItems) o;
		if (menuItems.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), menuItems.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "MenuItems [id=" + id + ", weight=" + weight + ", url=" + url + ", menu=" + menu + ", languages="
				+ languages + "]";
	}

	



}
