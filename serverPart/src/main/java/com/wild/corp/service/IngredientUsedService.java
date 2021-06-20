package com.wild.corp.service;


import com.wild.corp.model.IngredientUsed;
import com.wild.corp.model.User;

import java.util.List;
import java.util.Set;


public interface IngredientUsedService {


    void add(IngredientUsed ingredient);
    void delete(Integer ingredientId);
    void update(IngredientUsed ingredientUsed);
    IngredientUsed findById(Integer ingredientId);
    Set<IngredientUsed> refreshList(Integer menuId);

}
