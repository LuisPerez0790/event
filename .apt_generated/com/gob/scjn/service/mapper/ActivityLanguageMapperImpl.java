package com.gob.scjn.service.mapper;

import com.gob.scjn.domain.ActivityLanguage;
import com.gob.scjn.service.dto.ActivityLanguageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-24T19:22:04-0500",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.12.3.v20170228-1205, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class ActivityLanguageMapperImpl implements ActivityLanguageMapper {

    @Override
    public List<ActivityLanguageDTO> toDto(List<ActivityLanguage> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ActivityLanguageDTO> list = new ArrayList<ActivityLanguageDTO>( arg0.size() );
        for ( ActivityLanguage activityLanguage : arg0 ) {
            list.add( toDto( activityLanguage ) );
        }

        return list;
    }

    @Override
    public List<ActivityLanguage> toEntity(List<ActivityLanguageDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<ActivityLanguage> list = new ArrayList<ActivityLanguage>( arg0.size() );
        for ( ActivityLanguageDTO activityLanguageDTO : arg0 ) {
            list.add( toEntity( activityLanguageDTO ) );
        }

        return list;
    }

    @Override
    public ActivityLanguageDTO toDto(ActivityLanguage activityLanguage) {
        if ( activityLanguage == null ) {
            return null;
        }

        ActivityLanguageDTO activityLanguageDTO = new ActivityLanguageDTO();

        activityLanguageDTO.setAddress( activityLanguage.getAddress() );
        activityLanguageDTO.setDescription( activityLanguage.getDescription() );
        activityLanguageDTO.setId( activityLanguage.getId() );
        activityLanguageDTO.setLanguage( activityLanguage.getLanguage() );
        activityLanguageDTO.setName( activityLanguage.getName() );

        return activityLanguageDTO;
    }

    @Override
    public ActivityLanguage toEntity(ActivityLanguageDTO activityLanguageDTO) {
        if ( activityLanguageDTO == null ) {
            return null;
        }

        ActivityLanguage activityLanguage = new ActivityLanguage();

        activityLanguage.setId( activityLanguageDTO.getId() );
        activityLanguage.setName( activityLanguageDTO.getName() );
        activityLanguage.setDescription( activityLanguageDTO.getDescription() );
        activityLanguage.setAddress( activityLanguageDTO.getAddress() );
        activityLanguage.setLanguage( activityLanguageDTO.getLanguage() );

        return activityLanguage;
    }
}
