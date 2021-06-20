package com.wild.corp.controller;


import com.wild.corp.model.IngredientUsed;
import com.wild.corp.model.User;
import com.wild.corp.service.IngredientUsedService;
import com.wild.corp.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/ingredientUsed")
public class IngredientUsedController {

    public static final Logger logger = LoggerFactory.getLogger(IngredientUsedController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private IngredientUsedService ingredientUsedService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<IngredientUsed> add(@RequestBody IngredientUsed ingredientUsed) {
        logger.debug("add Ingredient");
        ingredientUsedService.add(ingredientUsed);

        return new ResponseEntity<>(ingredientUsed, HttpStatus.OK);
    }

    @RequestMapping(value = "/addIngredientUsedToListe", method = RequestMethod.POST)
    public ResponseEntity<IngredientUsed> addIngredientUsedToListe(@RequestBody IngredientUsed ingredientUsed, @RequestParam Integer weekNumber, @AuthenticationPrincipal User user) {
        logger.debug("addIngredientUsedToListe Ingredient");
        menuService.addIngredientUsedToListe(ingredientUsed, weekNumber, user);

        return new ResponseEntity<>(ingredientUsed, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<IngredientUsed> update(@RequestBody IngredientUsed ingredientUsed) {
        logger.debug("update IngredientUsed");
        ingredientUsedService.update(ingredientUsed);

        return new ResponseEntity<>(ingredientUsed, HttpStatus.OK);
    }

    @RequestMapping(value = "/refreshList", method = RequestMethod.PUT)
    public ResponseEntity<Set<IngredientUsed>> refreshList(@RequestParam Integer weekNumber) {
        logger.debug("refreshList weekNumber "+weekNumber);
        Set<IngredientUsed> listeCourse = ingredientUsedService.refreshList(weekNumber);
        return new ResponseEntity<>(listeCourse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllIngredientsOfWeek", method = RequestMethod.GET)
    public ResponseEntity<Set<IngredientUsed>> getAllIngredientsOfWeek(@RequestParam Integer weekNumber, @AuthenticationPrincipal User user) {
        logger.debug("getByWeek weekId "+weekNumber);
        Set<IngredientUsed> ingredientUseds = menuService.getAllIngredientsOfWeek(weekNumber,user);
        return new ResponseEntity<>(ingredientUseds, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(@RequestParam Integer id) {
        logger.debug("delete IngredientUsed");
        ingredientUsedService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }




}
