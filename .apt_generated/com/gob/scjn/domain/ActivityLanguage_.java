package com.gob.scjn.domain;

import com.gob.scjn.domain.enumeration.Language;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ActivityLanguage.class)
public abstract class ActivityLanguage_ {

	public static volatile SingularAttribute<ActivityLanguage, String> address;
	public static volatile SingularAttribute<ActivityLanguage, Long> activity;
	public static volatile SingularAttribute<ActivityLanguage, String> name;
	public static volatile SingularAttribute<ActivityLanguage, String> description;
	public static volatile SingularAttribute<ActivityLanguage, Language> language;
	public static volatile SingularAttribute<ActivityLanguage, Long> id;

}

