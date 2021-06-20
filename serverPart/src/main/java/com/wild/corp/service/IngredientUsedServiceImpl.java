package com.wild.corp.service;

import com.wild.corp.model.IngredientUsed;
import com.wild.corp.model.Jour;
import com.wild.corp.model.Menu;
import com.wild.corp.model.RecetteUsed;
import com.wild.corp.repositories.IngredientUsedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service("IngredientUsedService")
@Transactional
public class IngredientUsedServiceImpl implements IngredientUsedService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private IngredientUsedRepository ingredientUsedRepository;

    @Override
    public void add(IngredientUsed ingredient){
        ingredientUsedRepository.save(ingredient);
    }

    @Override
    public void delete(Integer ingredientId){
        ingredientUsedRepository.deleteById(ingredientId);
    }

    @Override
    public void update(IngredientUsed ingredientUsed){
        IngredientUsed ingre = findById(ingredientUsed.getId());

        ingre.setQuantite(ingredientUsed.getQuantite());
    }

    @Override
    public IngredientUsed findById(Integer ingredientId) {
        return ingredientUsedRepository.findById(ingredientId).get();
    }

    @Override
    public Set<IngredientUsed> refreshList(Integer menuId){
        Menu thisMenu = menuService.findById(menuId);

        for (IngredientUsed ingredientUsed:thisMenu.getIngredientsUsed()) {
            delete(ingredientUsed.getId());
        }
        thisMenu.getIngredientsUsed().clear();
        for (Jour jour:thisMenu.getSemaine()) {
            for (RecetteUsed recetteUsed:jour.getRecettesmidi()) {
                commonService.addRecetteToMenu(thisMenu, recetteUsed);
            }
            for (RecetteUsed recetteUsed:jour.getRecettessoir()) {
                commonService.addRecetteToMenu(thisMenu, recetteUsed);
            }
        }


        return thisMenu.getIngredientsUsed();
    }
}
