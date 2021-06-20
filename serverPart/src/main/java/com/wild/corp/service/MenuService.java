package com.wild.corp.service;


import com.wild.corp.model.IngredientUsed;
import com.wild.corp.model.Menu;
import com.wild.corp.model.Recette;
import com.wild.corp.model.User;

import java.util.List;
import java.util.Set;

public interface MenuService {

    void add(Menu menu, User user);

    void update(Menu menu);

    List<Menu> findAll();

    Menu addRecetteToMenu(Recette recette, Integer jourId, String repas);

    void updateQuantiteToRecette(Integer recetteId, Integer ajout);

    Set<IngredientUsed> getAllIngredientsOfWeek(Integer weekNumber, User user);

    void addIngredientUsedToListe(IngredientUsed ingredientUsed, Integer weekNumber, User user);

    Menu findByWeekAndUser(Integer week, User user);

    Menu findById(Integer menuId);

    Menu copyPrevious(Integer weekNumber,  User user);
}
