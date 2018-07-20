package com.gob.scjn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.gob.scjn.domain.enumeration.PageStatus;

/**
 * A Site.
 */
@Entity
@Table(name = "site")
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acronym")
    private String acronym;

    @Column(name = "jhi_date")
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PageStatus status;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "subtitle")
    private String subtitle;

    @OneToOne
    @JoinColumn(unique = true)
    private SiteColorPalette palette;

    @OneToOne
    @JoinColumn(unique = true)
    private SiteFooter footer;

    @OneToMany(mappedBy = "site")
    private Set<SitePage> sitePages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public Site acronym(String acronym) {
        this.acronym = acronym;
        return this;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Instant getDate() {
        return date;
    }

    public Site date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public PageStatus getStatus() {
        return status;
    }

    public Site status(PageStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PageStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public Site title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Site subtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public SiteColorPalette getPalette() {
        return palette;
    }

    public Site palette(SiteColorPalette siteColorPalette) {
        this.palette = siteColorPalette;
        return this;
    }

    public void setPalette(SiteColorPalette siteColorPalette) {
        this.palette = siteColorPalette;
    }

    public SiteFooter getFooter() {
        return footer;
    }

    public Site footer(SiteFooter siteFooter) {
        this.footer = siteFooter;
        return this;
    }

    public void setFooter(SiteFooter siteFooter) {
        this.footer = siteFooter;
    }

    public Set<SitePage> getSitePages() {
        return sitePages;
    }

    public Site sitePages(Set<SitePage> sitePages) {
        this.sitePages = sitePages;
        return this;
    }

    public Site addSitePage(SitePage sitePage) {
        this.sitePages.add(sitePage);
        sitePage.setSite(this);
        return this;
    }

    public Site removeSitePage(SitePage sitePage) {
        this.sitePages.remove(sitePage);
        sitePage.setSite(null);
        return this;
    }

    public void setSitePages(Set<SitePage> sitePages) {
        this.sitePages = sitePages;
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
        Site site = (Site) o;
        if (site.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), site.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Site{" +
            "id=" + getId() +
            ", acronym='" + getAcronym() + "'" +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", title='" + getTitle() + "'" +
            ", subtitle='" + getSubtitle() + "'" +
            "}";
    }
}