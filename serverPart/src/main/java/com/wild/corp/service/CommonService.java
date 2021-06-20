package com.wild.corp.service;

import com.wild.corp.model.IngredientUsed;
import com.wild.corp.model.Menu;
import com.wild.corp.model.RecetteUsed;


public interface CommonService {

    void addIngredientUsedToMenu(Menu menu, IngredientUsed ingredientUsed );
    void addRecetteToMenu(Menu menu, RecetteUsed recetteUsed );

    void updateQuantityByInteger(Menu menu, RecetteUsed recetteUsed ,Integer ajout);

    void deleteRecette(Menu menu, RecetteUsed recetteUsed );
}
