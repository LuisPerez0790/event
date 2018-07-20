package com.gob.scjn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.gob.scjn.domain.enumeration.Language;

/**
 * A SitePageLanguage.
 */
@Entity
@Table(name = "site_page_language")
public class SitePageLanguage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "except_page")
    private String exceptPage;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @ManyToOne
    @JsonIgnoreProperties("pages")
    private SitePage sitePage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExceptPage() {
        return exceptPage;
    }

    public SitePageLanguage exceptPage(String exceptPage) {
        this.exceptPage = exceptPage;
        return this;
    }

    public void setExceptPage(String exceptPage) {
        this.exceptPage = exceptPage;
    }

    public String getContent() {
        return content;
    }

    public SitePageLanguage content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public SitePageLanguage title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public SitePageLanguage language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public SitePage getSitePage() {
        return sitePage;
    }

    public SitePageLanguage sitePage(SitePage sitePage) {
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
        SitePageLanguage sitePageLanguage = (SitePageLanguage) o;
        if (sitePageLanguage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sitePageLanguage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SitePageLanguage{" +
            "id=" + getId() +
            ", exceptPage='" + getExceptPage() + "'" +
            ", content='" + getContent() + "'" +
            ", title='" + getTitle() + "'" +
            ", language='" + getLanguage() + "'" +
            "}";
    }
}
