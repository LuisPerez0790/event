package com.gob.scjn.domain;

import com.gob.scjn.domain.enumeration.PageStatus;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Site.class)
public abstract class Site_ {

	public static volatile SingularAttribute<Site, Instant> date;
	public static volatile SingularAttribute<Site, String> acronym;
	public static volatile SingularAttribute<Site, SiteFooter> footer;
	public static volatile SingularAttribute<Site, String> subtitle;
	public static volatile SetAttribute<Site, SitePage> sitePages;
	public static volatile SingularAttribute<Site, SiteColorPalette> palette;
	public static volatile SingularAttribute<Site, Long> id;
	public static volatile SingularAttribute<Site, String> title;
	public static volatile SingularAttribute<Site, PageStatus> status;

}

