package com.wild.corp.service;


import com.wild.corp.controller.RecetteController;
import com.wild.corp.model.*;
import com.wild.corp.repositories.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("MenuService")
@Transactional
public class MenuServiceImpl implements MenuService {
    public static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private JourService jourService;

    @Autowired
    private RecetteUsedService recetteUsedService;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void add(Menu menu, User user){
        menu.setUser(user);
        logger.debug("add menu.getNumeroSemaine() "+menu.getNumeroSemaine());

        Jour lundi = new Jour();
        lundi.setNomJour("Lundi");
        lundi.setMenu(menu);
        jourService.add(lundi);

        Jour mardi = new Jour();
        mardi.setNomJour("Mardi");
        mardi.setMenu(menu);
        jourService.add(mardi);

        Jour mercredi = new Jour();
        mercredi.setNomJour("Mercredi");
        mercredi.setMenu(menu);
        jourService.add(mercredi);

        Jour jeudi = new Jour();
        jeudi.setNomJour("Jeudi");
        jeudi.setMenu(menu);
        jourService.add(jeudi);

        Jour vendredi = new Jour();
        vendredi.setNomJour("Vendredi");
        vendredi.setMenu(menu);
        jourService.add(vendredi);

        Jour samedi = new Jour();
        samedi.setNomJour("Samedi");
        samedi.setMenu(menu);
        jourService.add(samedi);

        Jour dimanche = new Jour();
        dimanche.setNomJour("Dimanche");
        dimanche.setMenu(menu);
        jourService.add(dimanche);


        menuRepository.save(menu);
    }

    @Override
    public Menu addRecetteToMenu(Recette recette, Integer jourId, String repas){
        Jour jour = jourService.findById(jourId);
        RecetteUsed recetteUsed;
        if("midi".equals(repas)){
            recetteUsed = new RecetteUsed(recette, 2);
            recetteUsed.setMidi(jour);
            recetteUsedService.add(recetteUsed);
            jour.getRecettesmidi().add(recetteUsed);
        }else{
            recetteUsed = new RecetteUsed(recette, 2);
            recetteUsed.setSoir(jour);
            recetteUsedService.add(recetteUsed);
            jour.getRecettessoir().add(recetteUsed);
        }

        commonService.addRecetteToMenu(jour.getMenu(), recetteUsed);
        return jour.getMenu();
    }

    @Override
    public void updateQuantiteToRecette(Integer recetteId, Integer ajout){
        RecetteUsed recetteUsed = recetteUsedService.findById(recetteId);

        Menu menu = recetteUsed.getMidi() != null?recetteUsed.getMidi().getMenu():recetteUsed.getSoir().getMenu();


        commonService.updateQuantityByInteger(menu, recetteUsed, ajout);

        if(ajout > 0){
            recetteUsed.setQuantite(recetteUsed.getQuantite()+ajout);
        }else if(ajout < 0){
            recetteUsed.setQuantite(recetteUsed.getQuantite()-ajout);
        }
    }

    @Override
    public void update(Menu menuToUpdate) {
        Menu menu = menuRepository.getOne(menuToUpdate.getId());
        menu.setIngredientsUsed(menuToUpdate.getIngredientsUsed());
        menu.setSemaine(menuToUpdate.getSemaine());
    }


    @Override
    public Menu findByWeekAndUser(Integer week, User user){
        return menuRepository.findByNumeroSemaineAndUser(week, user);
    }


    @Override
    public Set<IngredientUsed> getAllIngredientsOfWeek(Integer menuId, User user){
        Menu menu = findByWeekAndUser(menuId, user);
        if(menu != null){
            return menu.getIngredientsUsed();
        }
        return null;
    }

    @Override
    public void addIngredientUsedToListe(IngredientUsed ingredientUsed, Integer weekNumber, User user){
        Menu menu = findByWeekAndUser(weekNumber, user);

        commonService.addIngredientUsedToMenu(menu, ingredientUsed);
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findById(Integer menuId) {
        return menuRepository.findById(menuId).get();
    }

    @Override
    public Menu copyPrevious(Integer weekNumber, User user) {
        Menu thisMenu = findByWeekAndUser(weekNumber, user);
        Menu previousMenu = findByWeekAndUser(weekNumber - 1, user);

        for (Jour previousJour : previousMenu.getSemaine()) {
            for (Jour thisJour : thisMenu.getSemaine()) {
                if(thisJour.getNomJour().equals(previousJour.getNomJour())) {


                    for (RecetteUsed recetteUsed:thisJour.getRecettesmidi()) {
                        recetteUsedService.delete(recetteUsed.getId());
                    }
                    for (RecetteUsed recetteMidi : previousJour.getRecettesmidi()) {
                        thisJour.getRecettesmidi().add(recetteUsedService.copy(recetteMidi, thisJour, "midi"));
                    }


                    for (RecetteUsed recetteUsed:thisJour.getRecettessoir()) {
                        recetteUsedService.delete(recetteUsed.getId());
                    }
                    for (RecetteUsed recetteSoir : previousJour.getRecettessoir()) {
                        thisJour.getRecettessoir().add(recetteUsedService.copy(recetteSoir, thisJour, "soir"));
                    }

                    jourService.add(thisJour);
                }
            }
        }

        thisMenu.getIngredientsUsed().clear();
        for (IngredientUsed ingredientUsed:previousMenu.getIngredientsUsed()) {
            thisMenu.getIngredientsUsed().add(ingredientUsed);
        }

        menuRepository.save(thisMenu);
        return thisMenu;
    }

}
