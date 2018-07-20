package com.gob.scjn.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CMS entity.
 */
public class CMSDTO implements Serializable {

    private Long id;

    private Long fileId;

    private Long eventUserId;

    private Long eventId;

    private Long activityId;

    private Long sitePageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getEventUserId() {
        return eventUserId;
    }

    public void setEventUserId(Long eventUserId) {
        this.eventUserId = eventUserId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

        CMSDTO cMSDTO = (CMSDTO) o;
        if (cMSDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cMSDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CMSDTO{" +
            "id=" + getId() +
            ", fileId=" + getFileId() +
            ", eventUser=" + getEventUserId() +
            ", event=" + getEventId() +
            ", activity=" + getActivityId() +
            ", sitePage=" + getSitePageId() +
            "}";
    }
}
