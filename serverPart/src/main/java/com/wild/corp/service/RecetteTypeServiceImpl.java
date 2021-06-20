package com.wild.corp.service;


import com.wild.corp.model.IngredientUsed;
import com.wild.corp.model.Recette;
import com.wild.corp.model.RecetteType;
import com.wild.corp.model.User;
import com.wild.corp.repositories.RecetteRepository;
import com.wild.corp.repositories.RecetteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("RecetteTypeService")
@Transactional
public class RecetteTypeServiceImpl implements RecetteTypeService {



    @Autowired
    private RecetteTypeRepository recetteTypeRepository;


    @Override
    public void add(String typeName, Integer ordre){
        if(!recetteTypeRepository.findById(typeName).isPresent()){
            RecetteType type = new RecetteType();
            type.setName(typeName);
            type.setOrdre(ordre);
            recetteTypeRepository.save(type);
        }
    }

    @Override
    public void update(RecetteType type) {


    }



    @Override
    public void delete(String typeName) {
        recetteTypeRepository.deleteById(typeName);
    }

    @Override
    public List<RecetteType> findAll() {
        return recetteTypeRepository.findAll();
    }

    @Override
    public RecetteType findById(String typeName) {
        return recetteTypeRepository.findById(typeName).get();
    }
}
