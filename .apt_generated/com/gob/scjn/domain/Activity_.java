package com.gob.scjn.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Activity.class)
public abstract class Activity_ {

	public static volatile SetAttribute<Activity, ActivityLanguage> languages;
	public static volatile SingularAttribute<Activity, Instant> endDate;
	public static volatile SingularAttribute<Activity, String> imageUrl;
	public static volatile SetAttribute<Activity, CMS> cms;
	public static volatile SingularAttribute<Activity, Long> id;
	public static volatile SingularAttribute<Activity, Event> event;
	public static volatile SingularAttribute<Activity, Instant> startDate;
	public static volatile SingularAttribute<Activity, Boolean> enabled;

}

