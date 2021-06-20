package com.wild.corp.service;


import com.wild.corp.model.*;
import com.wild.corp.repositories.EtapeRepository;
import com.wild.corp.repositories.RecetteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("RecetteService")
@Transactional
public class RecetteServiceImpl implements RecetteService {

    public static final Logger logger = LoggerFactory.getLogger(RecetteServiceImpl.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private EtapeRepository etapeRepository;

    @Autowired
    private RecetteRepository recetteRepository;

    @Autowired
    private RecetteNameService recetteNameService;

    @Autowired
    private RecetteTypeService recetteTypeService;

    @Autowired
    private IngredientUsedService ingredientUsedService;

    @Override
    public void add(Recette recetteTmp, User user){
        logger.debug("add Recette User ");
        Recette newrecette = new Recette();
        newrecette.setUser(user);
        recetteRepository.save(newrecette);

        RecetteName recetteName = recetteNameService.addRecette(newrecette, recetteTmp.getRecetteName().getNom(), recetteTmp.getRecetteName().getType().getName());
        newrecette.setRecetteName(recetteName);
        addStarr(newrecette, user);
        recetteRepository.save(newrecette);

    }



    @Override
    public Recette removeStarr(Recette recette, User user){
        Recette oldRecette = recetteRepository.getOne(recette.getId());

        Set<User> filteredSet = oldRecette.getStars().stream()
                .filter(s -> s.equals(user))
                .collect(Collectors.toSet());
        oldRecette.setStars(filteredSet);
        recetteRepository.save(oldRecette);
        return oldRecette;
    }



    @Override
    public Recette addStarr(Recette recette, User user){
        Recette oldRecette = recetteRepository.getOne(recette.getId());
        if(oldRecette.getStars() == null){
            oldRecette.setStars(new HashSet<>());
        }
        oldRecette.getStars().add(user);
        recetteRepository.save(oldRecette);
        return oldRecette;
    }



    @Override
    public void update(Recette recette) {
        Recette oldRecette = recetteRepository.getOne(recette.getId());

        for (IngredientUsed ingre:recette.getIngredientsUsed()) {
            if(ingre.getId() == null){
                ingre.setRecette(oldRecette);
                ingredientUsedService.add(ingre);
            }else{
                IngredientUsed oldIngre = ingredientUsedService.findById(ingre.getId());
                oldIngre.setQuantite(ingre.getQuantite());
            }
        }
        oldRecette.setImage(recette.getImage());
        oldRecette.setIngredientsUsed(recette.getIngredientsUsed());
        oldRecette.setRecetteName(recette.getRecetteName());

        for (Etape etape:recette.getEtapes()) {
            etape.setRecette(oldRecette);
            etapeRepository.save(etape);
        }

        oldRecette.setEtapes(recette.getEtapes());
        oldRecette.setPrepa(recette.getPrepa());

        oldRecette.setCuisson(recette.getCuisson());
        oldRecette.setPour(recette.getPour());
        oldRecette.setPourType(recette.getPourType());


    }

    @Override
    public Recette addIngredientToRecette(Recette recette, Integer ingredientId){
        Recette oldRecette = recetteRepository.getOne(recette.getId());

        return oldRecette;
    }

    @Override
    public void delete(Integer recetteId) {
        recetteRepository.deleteById(recetteId);
    }

    @Override
    public List<Recette> findAll() {
        return recetteRepository.findAll();
    }

    @Override
    public List<Recette> findAllByUser(User user) {

        List<Recette> recettesByUser = recetteRepository.findAllByUser(user);


        List<Recette> recettesByStarr = recetteRepository.findByStars(user).stream()
                .filter(r -> !r.getUser().equals(user))
                .collect(Collectors.toList());

        recettesByUser.addAll(recettesByStarr);
        return recettesByUser;
    }

    @Override
    public Map<String, List<Recette>> getAllByTypeAndStar(User user) {

        Map<String, List<Recette>> recettesByStarr = new HashMap<>();

        List<RecetteType> recetteTypes = recetteTypeService.findAll();
        List<Recette> recetteList = recetteRepository.findByStars(user);

        for (RecetteType recetteType :recetteTypes) {
            List<Recette> recetteListForType = recetteList.stream()
                    .filter(r -> r.getRecetteName().getType().equals(recetteType))
                    .collect(Collectors.toList());
            recettesByStarr.put(recetteType.getName(), recetteListForType);
        }

        return recettesByStarr;
    }




    @Override
    public List<Recette> findAllByType(String typeName) {
        return recetteRepository.findAllByType(typeName);
    }

    @Override
    public List<Recette> findAllByName(String recetteName) {
        return recetteRepository.findAllByRecetteName(recetteName);
    }



    @Override
    public Recette findById(Integer recetteId) {
        return recetteRepository.findById(recetteId).get();
    }
}
