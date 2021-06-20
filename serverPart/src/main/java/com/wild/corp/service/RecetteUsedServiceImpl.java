package com.wild.corp.service;


import com.wild.corp.model.Jour;
import com.wild.corp.model.Menu;
import com.wild.corp.model.RecetteUsed;
import com.wild.corp.repositories.RecetteRepository;
import com.wild.corp.repositories.RecetteUsedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("RecetteUsedService")
@Transactional
public class RecetteUsedServiceImpl implements RecetteUsedService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private JourService jourService;

    @Autowired
    private RecetteRepository recetteRepository;

    @Autowired
    private RecetteUsedRepository recetteUsedRepository;

    @Override
    public void add(RecetteUsed recetteUsed){
        recetteUsedRepository.save(recetteUsed);
    }

    @Override
    public RecetteUsed copy(RecetteUsed oldRecetteUsed,Jour newJour, String typeRepas){
        RecetteUsed newRecetteUsed = new RecetteUsed();

        newRecetteUsed.setQuantite(oldRecetteUsed.getQuantite());
        newRecetteUsed.setRecette(oldRecetteUsed.getRecette());
        recetteUsedRepository.save(newRecetteUsed);
        if("midi".equals(typeRepas)){
            newRecetteUsed.setMidi(newJour);
        }else {
            newRecetteUsed.setSoir(newJour);
        }
        return newRecetteUsed;
    }

    @Override
    public void update(RecetteUsed recetteUsed) {

    }

    @Override
    public void delete(Integer recetteUsedId) {
        RecetteUsed oldRecette = findById(recetteUsedId);

        Menu menu = oldRecette.getMidi() != null?oldRecette.getMidi().getMenu():oldRecette.getSoir().getMenu();

        commonService.deleteRecette(menu, oldRecette);
        recetteUsedRepository.deleteById(recetteUsedId);
    }

    @Override
    public RecetteUsed findById(Integer recetteUsedId) {
        return recetteUsedRepository.findById(recetteUsedId).get();
    }

}
