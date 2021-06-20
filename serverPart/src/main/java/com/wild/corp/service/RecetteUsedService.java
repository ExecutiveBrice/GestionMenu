package com.wild.corp.service;

import com.wild.corp.model.Jour;
import com.wild.corp.model.RecetteUsed;


public interface RecetteUsedService {

    void add(RecetteUsed recetteUsed);

    void update(RecetteUsed recetteUsed);

    RecetteUsed copy(RecetteUsed recetteUsed,Jour newJour, String typeRepas);

    void delete(Integer recetteUsedId);

    RecetteUsed findById(Integer recetteUsedId);
}
