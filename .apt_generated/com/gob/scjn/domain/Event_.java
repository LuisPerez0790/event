package com.gob.scjn.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Event.class)
public abstract class Event_ {

	public static volatile SingularAttribute<Event, Venue> venue;
	public static volatile SetAttribute<Event, EventLanguage> languages;
	public static volatile SingularAttribute<Event, Instant> endDate;
	public static volatile SingularAttribute<Event, String> acronym;
	public static volatile SetAttribute<Event, CMS> cms;
	public static volatile SingularAttribute<Event, String> url;
	public static volatile SingularAttribute<Event, Boolean> enabled;
	public static volatile SetAttribute<Event, EventUser> eventUsers;
	public static volatile SingularAttribute<Event, Site> site;
	public static volatile SetAttribute<Event, Activity> activities;
	public static volatile SingularAttribute<Event, String> imageUrl;
	public static volatile SingularAttribute<Event, Long> id;
	public static volatile SingularAttribute<Event, Instant> startDate;

}

