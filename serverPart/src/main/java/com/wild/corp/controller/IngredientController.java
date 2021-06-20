package com.wild.corp.controller;


import com.wild.corp.model.Ingredient;
import com.wild.corp.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    public static final Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Ingredient> add(@RequestBody Ingredient ingredient) {
        logger.debug("add Ingredient");
        ingredientService.add(ingredient);

        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<Ingredient> update(@RequestBody Ingredient ingredient) {
        logger.debug("update Ingredient");
        ingredientService.update(ingredient);

        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Ingredient>> getAll() {
        logger.debug("getAll Ingredient");
        List<Ingredient> ingredients = ingredientService.findAll();

        if(ingredients.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(@RequestParam Integer id) {
        logger.debug("delete Ingredient");
        ingredientService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }



}
