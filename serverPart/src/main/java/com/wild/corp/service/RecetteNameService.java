package com.wild.corp.service;

import com.wild.corp.model.Recette;
import com.wild.corp.model.RecetteName;
import com.wild.corp.model.RecetteType;
import com.wild.corp.model.User;

import java.util.List;
import java.util.Optional;

public interface RecetteNameService {

    void add(String recetteName, String type);
    RecetteName addRecette(Recette recette, String recetteName, String type);

    void update(RecetteName recetteName);
    List<RecetteName> findAllByUser(User user);
    List<RecetteName> findAll();
    List<RecetteName> findAllByType(String type);
    void delete(String recetteName);

    Optional<RecetteName> findById(String recetteName);
}
