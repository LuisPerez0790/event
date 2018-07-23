package com.gob.scjn.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A SitePage.
 */
@Entity
@Table(name = "site_page")
public class SitePage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "updated_date")
    private Instant updatedDate;

    @Column(name = "menu_entry")
    private String menuEntry;

    @Column(name = "jhi_order")
    private Long order;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "slug")
    private String slug;

    @ManyToOne
    @JsonIgnoreProperties("sitePages")
    private Site site;

    @OneToMany(mappedBy = "sitePage")
    private Set<SitePageLanguage> pages = new HashSet<>();

    @OneToMany(mappedBy = "sitePage")
    private Set<CMS> cms = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public SitePage creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public SitePage updatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getMenuEntry() {
        return menuEntry;
    }

    public SitePage menuEntry(String menuEntry) {
        this.menuEntry = menuEntry;
        return this;
    }

    public void setMenuEntry(String menuEntry) {
        this.menuEntry = menuEntry;
    }

    public Long getOrder() {
        return order;
    }

    public SitePage order(Long order) {
        this.order = order;
        return this;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Boolean isStatus() {
        return status;
    }

    public SitePage status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSlug() {
        return slug;
    }

    public SitePage slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Site getSite() {
        return site;
    }

    public SitePage site(Site site) {
        this.site = site;
        return this;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Set<SitePageLanguage> getPages() {
        return pages;
    }

    public SitePage pages(Set<SitePageLanguage> sitePageLanguages) {
        this.pages = sitePageLanguages;
        return this;
    }

    public SitePage addPage(SitePageLanguage sitePageLanguage) {
        this.pages.add(sitePageLanguage);
        sitePageLanguage.setSitePage(this);
        return this;
    }

    public SitePage removePage(SitePageLanguage sitePageLanguage) {
        this.pages.remove(sitePageLanguage);
        sitePageLanguage.setSitePage(null);
        return this;
    }

    public void setPages(Set<SitePageLanguage> sitePageLanguages) {
        this.pages = sitePageLanguages;
    }

    public Set<CMS> getCms() {
        return cms;
    }

    public SitePage cms(Set<CMS> cMS) {
        this.cms = cMS;
        return this;
    }

    public SitePage addCms(CMS cMS) {
        this.cms.add(cMS);
        cMS.setSitePage(this);
        return this;
    }

    public SitePage removeCms(CMS cMS) {
        this.cms.remove(cMS);
        cMS.setSitePage(null);
        return this;
    }

    public void setCms(Set<CMS> cMS) {
        this.cms = cMS;
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
        SitePage sitePage = (SitePage) o;
        if (sitePage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sitePage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SitePage{" +
            "id=" + getId() +
            ", creationDate='" + getCreationDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", menuEntry='" + getMenuEntry() + "'" +
            ", order=" + getOrder() +
            ", status='" + isStatus() + "'" +
            ", slug='" + getSlug() + "'" +
            "}";
    }
}
