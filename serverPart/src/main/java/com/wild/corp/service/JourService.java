package com.wild.corp.service;


import com.wild.corp.model.Jour;
import com.wild.corp.model.Menu;

import java.util.List;

public interface JourService {

    void add(Jour jour);


    Jour findById(Integer jourId);

    void update(Jour jour);

    List<Jour> findAll();


}
