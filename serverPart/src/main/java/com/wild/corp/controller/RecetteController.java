package com.wild.corp.controller;


import com.wild.corp.model.Recette;
import com.wild.corp.model.RecetteName;
import com.wild.corp.model.User;
import com.wild.corp.service.RecetteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/recette")
public class RecetteController {

    public static final Logger logger = LoggerFactory.getLogger(RecetteController.class);

    @Autowired
    private RecetteService recetteService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Recette> add(@RequestBody Recette recette, @AuthenticationPrincipal User user) {
        logger.debug("add Recette");

        recetteService.add(recette, user);

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Recette> update(@RequestBody Recette recette) {
        logger.debug("update Recette");
        recetteService.update(recette);

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }


    @RequestMapping(value = "/getAllByUser", method = RequestMethod.GET)
    public ResponseEntity<List<Recette>> getAllByUser(@AuthenticationPrincipal User user) {
        logger.debug("getAllByUser Recette");
        List<Recette> recettes = recetteService.findAllByUser(user);

        if(recettes.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }


    @RequestMapping(value = "/removeStarr", method = RequestMethod.PUT)
    public ResponseEntity<Recette> removeStarr(@RequestBody Recette recette, @AuthenticationPrincipal User user) {
        logger.debug("removeStarr Recette");
        recette = recetteService.removeStarr(recette, user);

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllByTypeAndStar", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<Recette>>> getAllByTypeAndStar(@AuthenticationPrincipal User user) {
        logger.debug("getAllByTypeAndStar Recette");
        Map<String, List<Recette>>  recettes = recetteService.getAllByTypeAndStar(user);

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }



    @RequestMapping(value = "/addStarr", method = RequestMethod.PUT)
    public ResponseEntity<Recette> addStarr(@RequestBody Recette recette, @AuthenticationPrincipal User user) {
        logger.debug("addStarr Recette");
        recette = recetteService.addStarr(recette, user);

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }


    @RequestMapping(value = "/getAllByType", method = RequestMethod.GET)
    public ResponseEntity<List<Recette>> getAllByType(@RequestParam String type) {
        logger.debug("getAllByType Recette "+type);
        List<Recette> recettes = recetteService.findAllByType(type);

        if(recettes.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllByName", method = RequestMethod.GET)
    public ResponseEntity<List<Recette>> getAllByName(@RequestParam String name) {
        logger.debug("getAllByName Recette "+name);
        List<Recette> recettes = recetteService.findAllByName(name);

        if(recettes.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }


    @RequestMapping(value = "/addIngredientToRecette", method = RequestMethod.PUT)
    public ResponseEntity<Recette> addIngredientToRecette(@RequestBody Recette recette, @RequestParam Integer ingredientId) {
        logger.debug("addIngredientToRecette Recette");
        recetteService.addIngredientToRecette(recette, ingredientId);

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }


    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<Recette> getById(@RequestParam Integer recetteId) {
        logger.debug("getById Recette "+recetteId);
        Recette recette = recetteService.findById(recetteId);

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Recette>> getAll() {
        logger.debug("getAll Recette");
        List<Recette> recettes = recetteService.findAll();

        if(recettes.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(@RequestParam Integer id) {
        logger.debug("delete Recette "+id);
        recetteService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
