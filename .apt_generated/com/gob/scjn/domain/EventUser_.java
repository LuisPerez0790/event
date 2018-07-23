package com.gob.scjn.domain;

import com.gob.scjn.domain.enumeration.UserType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EventUser.class)
public abstract class EventUser_ {

	public static volatile SingularAttribute<EventUser, String> lastName;
	public static volatile SingularAttribute<EventUser, String> institution;
	public static volatile SingularAttribute<EventUser, String> imageUrl;
	public static volatile SingularAttribute<EventUser, String> name;
	public static volatile SetAttribute<EventUser, CMS> cms;
	public static volatile SingularAttribute<EventUser, String> description;
	public static volatile SetAttribute<EventUser, Event> sites;
	public static volatile SingularAttribute<EventUser, Long> id;
	public static volatile SingularAttribute<EventUser, String> position;
	public static volatile SingularAttribute<EventUser, UserType> userType;
	public static volatile SingularAttribute<EventUser, String> userName;
	public static volatile SingularAttribute<EventUser, String> email;

}

