package com.wild.corp.service;


import com.wild.corp.model.Ingredient;

import java.util.List;

public interface IngredientService {

    void add(Ingredient ingredient);
    void delete(Integer ingredientId);
    void update(Ingredient ingredient);

    List<Ingredient> findAll();

    Ingredient findById(Integer ingredientId);
}
