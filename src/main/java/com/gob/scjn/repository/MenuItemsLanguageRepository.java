package com.gob.scjn.repository;

import com.gob.scjn.domain.MenuItemsLanguage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MenuItemsLanguage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MenuItemsLanguageRepository extends JpaRepository<MenuItemsLanguage, Long> {

}
