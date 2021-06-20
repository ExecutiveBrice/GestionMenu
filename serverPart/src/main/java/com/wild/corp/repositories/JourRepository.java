package com.wild.corp.repositories;


import com.wild.corp.model.Jour;
import com.wild.corp.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JourRepository extends JpaRepository<Jour, Integer> {

    Jour getOne(Integer menuId);

    List<Jour> findAll();


}
