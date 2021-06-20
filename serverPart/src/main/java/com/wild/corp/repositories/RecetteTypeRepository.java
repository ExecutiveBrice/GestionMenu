package com.wild.corp.repositories;


import com.wild.corp.model.Recette;
import com.wild.corp.model.RecetteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecetteTypeRepository extends JpaRepository<RecetteType, String> {

    RecetteType getOne(String type);

    List<RecetteType> findAll();
}
