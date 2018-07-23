package com.gob.scjn.domain;

import com.gob.scjn.domain.enumeration.Language;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EventLanguage.class)
public abstract class EventLanguage_ {

	public static volatile SingularAttribute<EventLanguage, String> address;
	public static volatile SingularAttribute<EventLanguage, String> name;
	public static volatile SingularAttribute<EventLanguage, String> description;
	public static volatile SingularAttribute<EventLanguage, Language> language;
	public static volatile SingularAttribute<EventLanguage, Long> id;
	public static volatile SingularAttribute<EventLanguage, Long> event;

}

