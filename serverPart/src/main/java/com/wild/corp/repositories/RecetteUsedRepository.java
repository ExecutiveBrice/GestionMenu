package com.wild.corp.repositories;


import com.wild.corp.model.RecetteUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecetteUsedRepository extends JpaRepository<RecetteUsed, Integer> {

    RecetteUsed getOne(Integer recetteId);

    List<RecetteUsed> findAll();
}
