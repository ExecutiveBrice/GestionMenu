package com.wild.corp.repositories;

import com.wild.corp.model.IngredientUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IngredientUsedRepository extends JpaRepository<IngredientUsed, Integer> {

    IngredientUsed getOne(Integer ingredientId);

    List<IngredientUsed> findAll();
}
