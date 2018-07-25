package com.gob.scjn.domain;

import com.gob.scjn.domain.enumeration.Language;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MenuItemsLanguage.class)
public abstract class MenuItemsLanguage_ {

	public static volatile SingularAttribute<MenuItemsLanguage, String> name;
	public static volatile SingularAttribute<MenuItemsLanguage, MenuItems> menuItems;
	public static volatile SingularAttribute<MenuItemsLanguage, Language> language;
	public static volatile SingularAttribute<MenuItemsLanguage, Long> id;

}

