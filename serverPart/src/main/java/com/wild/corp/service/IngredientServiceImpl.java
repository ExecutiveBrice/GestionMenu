package com.wild.corp.service;


import com.wild.corp.model.Ingredient;
import com.wild.corp.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("IngredientService")
@Transactional
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void add(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    @Override
    public void delete(Integer ingredientId){
        ingredientRepository.deleteById(ingredientId);
    }

    @Override
    public void update(Ingredient ingredient) {
        Ingredient ingre = findById(ingredient.getId());
        ingre.setNom(ingredient.getNom());
        ingre.setRayon(ingredient.getRayon());
        ingre.setUnite(ingredient.getUnite());

        ingre.setCoef(ingredient.getCoef());
        ingre.setUniteVente(ingredient.getUniteVente());
        ingre.setProteines(ingredient.getProteines());
        ingre.setGlucides(ingredient.getGlucides());
        ingre.setLipides(ingredient.getLipides());
        ingre.setAcidegras(ingredient.getAcidegras());
        ingre.setSucres(ingredient.getSucres());
        ingre.setKcal(ingredient.getKcal());
        ingre.setKjoule(ingredient.getKjoule());

    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findById(Integer ingredientId) {
        return ingredientRepository.findById(ingredientId).get();
    }

}
