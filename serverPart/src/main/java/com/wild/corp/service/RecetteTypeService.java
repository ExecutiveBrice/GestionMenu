package com.wild.corp.service;

import com.wild.corp.model.Recette;
import com.wild.corp.model.RecetteType;
import com.wild.corp.model.User;

import java.util.List;

public interface RecetteTypeService {

    void add(String typeName, Integer ordre);

    void update(RecetteType type);

    void delete(String typeName);

    public List<RecetteType> findAll();

    RecetteType findById(String typeName);
}
