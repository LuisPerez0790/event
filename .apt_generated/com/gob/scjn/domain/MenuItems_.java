package com.gob.scjn.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MenuItems.class)
public abstract class MenuItems_ {

	public static volatile SetAttribute<MenuItems, MenuItemsLanguage> languages;
	public static volatile SingularAttribute<MenuItems, Integer> weight;
	public static volatile SingularAttribute<MenuItems, Long> id;
	public static volatile SingularAttribute<MenuItems, Menu> menu;
	public static volatile SingularAttribute<MenuItems, String> url;

}

