package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Activity;
import com.gob.scjn.domain.CMS;
import com.gob.scjn.domain.Event;
import com.gob.scjn.domain.EventUser;
import com.gob.scjn.domain.SitePage;
import com.gob.scjn.service.dto.CMSDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-21T16:28:42-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class CMSMapperImpl implements CMSMapper {

    @Autowired
    private EventUserMapper eventUserMapper;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private SitePageMapper sitePageMapper;

    @Override
    public List<CMSDTO> toDto(List<CMS> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<CMSDTO> list = new ArrayList<CMSDTO>( arg0.size() );
        for ( CMS cMS : arg0 ) {
            list.add( toDto( cMS ) );
        }

        return list;
    }

    @Override
    public List<CMS> toEntity(List<CMSDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<CMS> list = new ArrayList<CMS>( arg0.size() );
        for ( CMSDTO cMSDTO : arg0 ) {
            list.add( toEntity( cMSDTO ) );
        }

        return list;
    }

    @Override
    public CMSDTO toDto(CMS cMS) {
        if ( cMS == null ) {
            return null;
        }

        CMSDTO cMSDTO = new CMSDTO();

        Long id = cMSEventUserId( cMS );
        if ( id != null ) {
            cMSDTO.setEventUserId( id );
        }
        Long id1 = cMSEventId( cMS );
        if ( id1 != null ) {
            cMSDTO.setEventId( id1 );
        }
        Long id2 = cMSActivityId( cMS );
        if ( id2 != null ) {
            cMSDTO.setActivityId( id2 );
        }
        Long id3 = cMSSitePageId( cMS );
        if ( id3 != null ) {
            cMSDTO.setSitePageId( id3 );
        }
        cMSDTO.setFileId( cMS.getFileId() );
        cMSDTO.setId( cMS.getId() );

        return cMSDTO;
    }

    @Override
    public CMS toEntity(CMSDTO cMSDTO) {
        if ( cMSDTO == null ) {
            return null;
        }

        CMS cMS = new CMS();

        cMS.setEventUser( eventUserMapper.fromId( cMSDTO.getEventUserId() ) );
        cMS.setSitePage( sitePageMapper.fromId( cMSDTO.getSitePageId() ) );
        cMS.setActivity( activityMapper.fromId( cMSDTO.getActivityId() ) );
        cMS.setEvent( eventMapper.fromId( cMSDTO.getEventId() ) );
        cMS.setFileId( cMSDTO.getFileId() );
        cMS.setId( cMSDTO.getId() );

        return cMS;
    }

    private Long cMSEventUserId(CMS cMS) {
        if ( cMS == null ) {
            return null;
        }
        EventUser eventUser = cMS.getEventUser();
        if ( eventUser == null ) {
            return null;
        }
        Long id = eventUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long cMSEventId(CMS cMS) {
        if ( cMS == null ) {
            return null;
        }
        Event event = cMS.getEvent();
        if ( event == null ) {
            return null;
        }
        Long id = event.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long cMSActivityId(CMS cMS) {
        if ( cMS == null ) {
            return null;
        }
        Activity activity = cMS.getActivity();
        if ( activity == null ) {
            return null;
        }
        Long id = activity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long cMSSitePageId(CMS cMS) {
        if ( cMS == null ) {
            return null;
        }
        SitePage sitePage = cMS.getSitePage();
        if ( sitePage == null ) {
            return null;
        }
        Long id = sitePage.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
