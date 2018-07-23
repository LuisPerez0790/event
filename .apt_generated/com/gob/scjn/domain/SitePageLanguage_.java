package com.gob.scjn.domain;

import com.gob.scjn.domain.enumeration.Language;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SitePageLanguage.class)
public abstract class SitePageLanguage_ {

	public static volatile SingularAttribute<SitePageLanguage, String> exceptPage;
	public static volatile SingularAttribute<SitePageLanguage, Language> language;
	public static volatile SingularAttribute<SitePageLanguage, SitePage> sitePage;
	public static volatile SingularAttribute<SitePageLanguage, Long> id;
	public static volatile SingularAttribute<SitePageLanguage, String> title;
	public static volatile SingularAttribute<SitePageLanguage, String> content;

}

