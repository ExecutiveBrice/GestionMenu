package com.wild.corp.service;

import com.wild.corp.model.Recette;
import com.wild.corp.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecetteService {

    void add(Recette recette, User user);

    void update(Recette recette);

    Recette addIngredientToRecette(Recette recette, Integer ingredientId);

    List<Recette> findAll();

    List<Recette> findAllByUser(User user);

    List<Recette> findAllByType(String typeName);

    List<Recette> findAllByName(String recetteName);

    Recette addStarr(Recette recette, User user);

    Recette removeStarr(Recette recette, User user);

    Map<String, List<Recette>> getAllByTypeAndStar(User user);

    void delete(Integer recetteId);

    Recette findById(Integer recetteId);
}
