package com.wild.corp.controller;


import com.wild.corp.model.RecetteType;
import com.wild.corp.service.RecetteTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/recetteType")
public class RecetteTypeController {

    public static final Logger logger = LoggerFactory.getLogger(RecetteTypeController.class);

    @Autowired
    private RecetteTypeService recetteTypeService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<RecetteType> add(@RequestBody RecetteType recetteType) {
        logger.debug("add RecetteType");

        recetteTypeService.add(recetteType.getName(), recetteType.getOrdre());
        return new ResponseEntity<>(recetteType, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<RecetteType> update(@RequestBody RecetteType recette) {
        logger.debug("update RecetteType");
        recetteTypeService.update(recette);

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<RecetteType> getById(@RequestParam String nom) {
        logger.debug("getById RecetteType");
        RecetteType recetteType = recetteTypeService.findById(nom);

        return new ResponseEntity<>(recetteType, HttpStatus.OK);
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<RecetteType>> getAll() {
        logger.debug("getAll RecetteType");
        List<RecetteType> recetteTypes = recetteTypeService.findAll();

        if(recetteTypes.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recetteTypes, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam String nom) {
        logger.debug("delete RecetteType");
        recetteTypeService.delete(nom);

        return new ResponseEntity<>(nom, HttpStatus.OK);
    }

}
