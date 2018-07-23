package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.Activity;
import com.gob.scjn.domain.ActivityLanguage;
import com.gob.scjn.service.dto.ActivityLanguageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-21T14:51:12-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class ActivityLanguageMapperImpl implements ActivityLanguageMapper {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<ActivityLanguage> toEntity(List<ActivityLanguageDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ActivityLanguage> list = new ArrayList<ActivityLanguage>( dtoList.size() );
        for ( ActivityLanguageDTO activityLanguageDTO : dtoList ) {
            list.add( toEntity( activityLanguageDTO ) );
        }

        return list;
    }

    @Override
    public List<ActivityLanguageDTO> toDto(List<ActivityLanguage> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ActivityLanguageDTO> list = new ArrayList<ActivityLanguageDTO>( entityList.size() );
        for ( ActivityLanguage activityLanguage : entityList ) {
            list.add( toDto( activityLanguage ) );
        }

        return list;
    }

    @Override
    public ActivityLanguageDTO toDto(ActivityLanguage activityLanguage) {
        if ( activityLanguage == null ) {
            return null;
        }

        ActivityLanguageDTO activityLanguageDTO = new ActivityLanguageDTO();

        Long id = activityLanguageActivityId( activityLanguage );
        if ( id != null ) {
            activityLanguageDTO.setActivityId( id );
        }
        activityLanguageDTO.setId( activityLanguage.getId() );
        activityLanguageDTO.setName( activityLanguage.getName() );
        activityLanguageDTO.setDescription( activityLanguage.getDescription() );
        activityLanguageDTO.setAddress( activityLanguage.getAddress() );
        activityLanguageDTO.setLanguage( activityLanguage.getLanguage() );

        return activityLanguageDTO;
    }

    @Override
    public ActivityLanguage toEntity(ActivityLanguageDTO activityLanguageDTO) {
        if ( activityLanguageDTO == null ) {
            return null;
        }

        ActivityLanguage activityLanguage = new ActivityLanguage();

        activityLanguage.setActivity( activityMapper.fromId( activityLanguageDTO.getActivityId() ) );
        activityLanguage.setId( activityLanguageDTO.getId() );
        activityLanguage.setName( activityLanguageDTO.getName() );
        activityLanguage.setDescription( activityLanguageDTO.getDescription() );
        activityLanguage.setAddress( activityLanguageDTO.getAddress() );
        activityLanguage.setLanguage( activityLanguageDTO.getLanguage() );

        return activityLanguage;
    }

    private Long activityLanguageActivityId(ActivityLanguage activityLanguage) {
        if ( activityLanguage == null ) {
            return null;
        }
        Activity activity = activityLanguage.getActivity();
        if ( activity == null ) {
            return null;
        }
        Long id = activity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
