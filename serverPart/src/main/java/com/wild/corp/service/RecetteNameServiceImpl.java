package com.wild.corp.service;


import com.wild.corp.model.*;
import com.wild.corp.repositories.RecetteNameRepository;
import com.wild.corp.repositories.RecetteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("RecetteNameService")
@Transactional
public class RecetteNameServiceImpl implements RecetteNameService {
    public static final Logger logger = LoggerFactory.getLogger(RecetteNameServiceImpl.class);


    @Autowired
    private RecetteNameRepository recetteNameRepository;
    @Autowired
    private RecetteTypeService recetteTypeService;

    @Autowired
    private RecetteService recetteService;

    @Override
    public void add(String nom, String type){
        logger.debug("add RecetteName with "+nom +" type "+type);

        RecetteName recetteName = new RecetteName();
        recetteName.setNom(nom);
        recetteName.setType(recetteTypeService.findById(type));
        recetteNameRepository.save(recetteName);

    }


    @Override
    public RecetteName addRecette(Recette recette, String recetteName, String type){
        logger.debug("addRecette RecetteName findById "+recetteName);
        if(!recetteNameRepository.findById(recetteName).isPresent()){

            add(recetteName, type);
        }
        RecetteName oldrecetteName = recetteNameRepository.findById(recetteName).get();

        if(oldrecetteName.getRecettes() == null){
            oldrecetteName.setRecettes(new ArrayList<>());
        }
        oldrecetteName.getRecettes().add(recette);
        recetteNameRepository.save(oldrecetteName);

        return oldrecetteName;
    }


    @Override
    public void update(RecetteName recetteName) {
        RecetteName oldRecetteName = findById(recetteName.getNom()).get();
        oldRecetteName.setRecettes(recetteName.getRecettes());
        recetteNameRepository.save(oldRecetteName);

    }

    @Override
    public List<RecetteName> findAllByType(String type){
       return recetteNameRepository.findAllByType(recetteTypeService.findById(type));
    }
    @Override
    public List<RecetteName> findAllByUser(User user){
        return recetteNameRepository.findAllByUser(user);
    }

    @Override
    public void delete(String nom) {
        recetteNameRepository.deleteById(nom);
    }

    @Override
    public List<RecetteName> findAll() {
        return recetteNameRepository.findAll();
    }

    @Override
    public Optional<RecetteName> findById(String nom) {
        return recetteNameRepository.findById(nom);
    }
}
