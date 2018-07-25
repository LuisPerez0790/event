package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Activity;
import com.gob.scjn.domain.ActivityLanguage;
import com.gob.scjn.domain.Event;
import com.gob.scjn.service.dto.ActivityDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-24T19:22:04-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class ActivityMapperImpl implements ActivityMapper {

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<ActivityDTO> toDto(List<Activity> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ActivityDTO> list = new ArrayList<ActivityDTO>( arg0.size() );
        for ( Activity activity : arg0 ) {
            list.add( toDto( activity ) );
        }

        return list;
    }

    @Override
    public List<Activity> toEntity(List<ActivityDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Activity> list = new ArrayList<Activity>( arg0.size() );
        for ( ActivityDTO activityDTO : arg0 ) {
            list.add( toEntity( activityDTO ) );
        }

        return list;
    }

    @Override
    public ActivityDTO toDto(Activity activity) {
        if ( activity == null ) {
            return null;
        }

        ActivityDTO activityDTO = new ActivityDTO();

        Long id = activityEventId( activity );
        if ( id != null ) {
            activityDTO.setEventId( id );
        }
        activityDTO.setId( activity.getId() );
        activityDTO.setStartDate( activity.getStartDate() );
        activityDTO.setEndDate( activity.getEndDate() );
        activityDTO.setImageUrl( activity.getImageUrl() );
        activityDTO.setEnabled( activity.isEnabled() );
        Set<ActivityLanguage> set = activity.getLanguages();
        if ( set != null ) {
            activityDTO.setLanguages( new HashSet<ActivityLanguage>( set ) );
        }
        else {
            activityDTO.setLanguages( null );
        }

        return activityDTO;
    }

    @Override
    public Activity toEntity(ActivityDTO activityDTO) {
        if ( activityDTO == null ) {
            return null;
        }

        Activity activity = new Activity();

        activity.setEvent( eventMapper.fromId( activityDTO.getEventId() ) );
        activity.setId( activityDTO.getId() );
        activity.setStartDate( activityDTO.getStartDate() );
        activity.setEndDate( activityDTO.getEndDate() );
        activity.setImageUrl( activityDTO.getImageUrl() );
        activity.setEnabled( activityDTO.isEnabled() );
        Set<ActivityLanguage> set = activityDTO.getLanguages();
        if ( set != null ) {
            activity.setLanguages( new HashSet<ActivityLanguage>( set ) );
        }
        else {
            activity.setLanguages( null );
        }

        return activity;
    }

    private Long activityEventId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        Event event = activity.getEvent();
        if ( event == null ) {
            return null;
        }
        Long id = event.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
