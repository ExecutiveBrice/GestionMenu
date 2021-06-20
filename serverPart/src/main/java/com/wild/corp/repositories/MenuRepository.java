package com.wild.corp.repositories;


import com.wild.corp.model.Menu;
import com.wild.corp.model.Recette;
import com.wild.corp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    Menu getOne(Integer menuId);

    List<Menu> findAll();

    List<Menu> findByNumeroSemaine(Integer week);

    Menu findByNumeroSemaineAndUser(Integer week, User user);

}
