package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.gob.scjn.domain.enumeration.Language;

/**
 * A DTO for the SitePageLanguage entity.
 */
public class SitePageLanguageDTO implements Serializable {

    private Long id;

    @Lob
    private String exceptPage;

    @Lob
    private String content;

    private String title;

    private Language language;

    private Long sitePageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExceptPage() {
        return exceptPage;
    }

    public void setExceptPage(String exceptPage) {
        this.exceptPage = exceptPage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getSitePageId() {
        return sitePageId;
    }

    public void setSitePageId(Long sitePageId) {
        this.sitePageId = sitePageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SitePageLanguageDTO sitePageLanguageDTO = (SitePageLanguageDTO) o;
        if (sitePageLanguageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sitePageLanguageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SitePageLanguageDTO{" +
            "id=" + getId() +
            ", exceptPage='" + getExceptPage() + "'" +
            ", content='" + getContent() + "'" +
            ", title='" + getTitle() + "'" +
            ", language='" + getLanguage() + "'" +
            ", sitePage=" + getSitePageId() +
            "}";
    }
}
