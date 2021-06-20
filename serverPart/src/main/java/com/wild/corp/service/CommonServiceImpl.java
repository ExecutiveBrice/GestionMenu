package com.wild.corp.service;


import com.wild.corp.model.IngredientUsed;
import com.wild.corp.model.Menu;
import com.wild.corp.model.RecetteUsed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("CommonService")
public class CommonServiceImpl implements CommonService {

    public static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);


    @Autowired
    private IngredientUsedService ingredientUsedService;

    @Override
    public void addIngredientUsedToMenu(Menu menu, IngredientUsed ingredientUsed ){

            logger.debug("addIngredientUsedToMenu "+ingredientUsed.getIngredient().getNom());

            if(!menu.getIngredientsUsed().contains(ingredientUsed)){
                IngredientUsed ingredientUsedMenu = new IngredientUsed();
                ingredientUsedMenu.setIngredient(ingredientUsed.getIngredient());
                ingredientUsedMenu.setQuantite(ingredientUsed.getQuantite());
                ingredientUsedMenu.setMenu(menu);
                ingredientUsedService.add(ingredientUsedMenu);
                menu.getIngredientsUsed().add(ingredientUsedMenu);
            }else{
                for (IngredientUsed ingredientUsedMenu:menu.getIngredientsUsed()) {
                    if(ingredientUsedMenu.equals(ingredientUsed)){
                        ingredientUsedMenu.setQuantite(ingredientUsedMenu.getQuantite() + ingredientUsed.getQuantite());
                    }
                }
            }
        }


    @Override
    public void addRecetteToMenu(Menu menu, RecetteUsed recetteUsed ){
        for (IngredientUsed ingredientUsedRecette:recetteUsed.getRecette().getIngredientsUsed()) {

            logger.debug("updateQuantity "+ingredientUsedRecette.getIngredient().getNom());
            logger.debug("updateQuantity "+recetteUsed.getRecette().getRecetteName());

            if(!menu.getIngredientsUsed().contains(ingredientUsedRecette)){




                Integer quantite = ingredientUsedRecette.getQuantite() * recetteUsed.getQuantite();
                IngredientUsed ingredientUsedMenu = new IngredientUsed();
                ingredientUsedMenu.setIngredient(ingredientUsedRecette.getIngredient());
                ingredientUsedMenu.setQuantite(quantite);
                ingredientUsedMenu.setMenu(menu);
                ingredientUsedService.add(ingredientUsedMenu);
                menu.getIngredientsUsed().add(ingredientUsedMenu);
            }else{

                for (IngredientUsed ingredientUsedMenu:menu.getIngredientsUsed()) {

                    if(ingredientUsedMenu.equals(ingredientUsedRecette)){

                        Integer quantite = ingredientUsedRecette.getQuantite() * recetteUsed.getQuantite();
                        ingredientUsedMenu.setQuantite(ingredientUsedMenu.getQuantite() + quantite);
                    }

                }
            }
        }
    }

    @Override
    public void updateQuantityByInteger(Menu menu, RecetteUsed recetteUsed, Integer ajout){
        for (IngredientUsed ingredientUsedRecette:recetteUsed.getRecette().getIngredientsUsed()) {

            if(!menu.getIngredientsUsed().contains(ingredientUsedRecette)){
                if(ajout > 0){
                    Integer quantite = ingredientUsedRecette.getQuantite() * recetteUsed.getQuantite();
                    IngredientUsed ingredientUsedMenu = new IngredientUsed();
                    ingredientUsedMenu.setIngredient(ingredientUsedRecette.getIngredient());
                    ingredientUsedMenu.setQuantite(quantite);
                    ingredientUsedMenu.setMenu(menu);
                    ingredientUsedService.add(ingredientUsedMenu);
                    menu.getIngredientsUsed().add(ingredientUsedMenu);
                }
            }else{
                if(ajout > 0){
                    for (IngredientUsed ingredientUsedMenu:menu.getIngredientsUsed()) {
                        if(ingredientUsedMenu.getIngredient().equals(ingredientUsedRecette.getIngredient())){
                            Integer quantite = ingredientUsedRecette.getQuantite()*ajout;
                            ingredientUsedMenu.setQuantite(ingredientUsedMenu.getQuantite() + quantite);
                        }
                    }
                }else if(ajout < 0){
                    for (IngredientUsed ingredientUsedMenu:menu.getIngredientsUsed()) {
                        if(ingredientUsedMenu.equals(ingredientUsedRecette)){
                            Integer quantite = ingredientUsedRecette.getQuantite()*ajout;
                            quantite = ingredientUsedMenu.getQuantite() + quantite;

                            if(quantite <= 0){
                                menu.getIngredientsUsed().remove(ingredientUsedMenu);
                            }else {
                                ingredientUsedMenu.setQuantite(quantite);
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void deleteRecette(Menu menu, RecetteUsed recetteUsed){
        for (IngredientUsed ingredientUsedRecette:recetteUsed.getRecette().getIngredientsUsed()) {

            for (IngredientUsed ingredientUsedMenu : menu.getIngredientsUsed()) {
                if (ingredientUsedMenu.equals(ingredientUsedRecette)) {
                    Integer quantite = ingredientUsedRecette.getQuantite() * recetteUsed.getQuantite();
                    quantite = ingredientUsedMenu.getQuantite() - quantite;

                    if (quantite <= 0) {
                        menu.getIngredientsUsed().remove(ingredientUsedMenu);
                    } else {
                        ingredientUsedMenu.setQuantite(quantite);
                    }
                }
            }
        }

    }


}
