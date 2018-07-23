package com.gob.scjn.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SitePage.class)
public abstract class SitePage_ {

	public static volatile SingularAttribute<SitePage, Site> site;
	public static volatile SetAttribute<SitePage, SitePageLanguage> pages;
	public static volatile SetAttribute<SitePage, CMS> cms;
	public static volatile SingularAttribute<SitePage, Long> id;
	public static volatile SingularAttribute<SitePage, Instant> updatedDate;
	public static volatile SingularAttribute<SitePage, Instant> creationDate;
	public static volatile SingularAttribute<SitePage, String> menuEntry;
	public static volatile SingularAttribute<SitePage, String> slug;
	public static volatile SingularAttribute<SitePage, Long> order;
	public static volatile SingularAttribute<SitePage, Boolean> status;

}

