package com.wild.corp.service;


import com.wild.corp.model.Jour;
import com.wild.corp.model.Menu;
import com.wild.corp.repositories.JourRepository;
import com.wild.corp.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("JourService")
@Transactional
public class JourServiceImpl implements JourService {

    @Autowired
    private JourRepository jourRepository;

    @Override
    public void add(Jour jour){
        jourRepository.save(jour);
    }

    @Override
    public Jour findById(Integer jourId){
        return jourRepository.findById(jourId).get();
    }


    @Override
    public void update(Jour menu) {

    }

    @Override
    public List<Jour> findAll() {
        return jourRepository.findAll();
    }

}
