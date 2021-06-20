package com.wild.corp.controller;


import com.wild.corp.model.Menu;
import com.wild.corp.model.Recette;
import com.wild.corp.model.User;
import com.wild.corp.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuController {

    public static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Menu> add(@RequestBody Menu menu, @AuthenticationPrincipal User user) {
        logger.debug("add Menu {} to user {}", menu.getId(), user.getId());
        menuService.add(menu, user);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @RequestMapping(value = "/addRecetteToMenu", method = RequestMethod.PUT)
    public ResponseEntity<Menu> update(@RequestBody Recette recette, @RequestParam Integer jourId, @RequestParam String repas) {
        logger.debug("addRecetteToMenu Menu");
        Menu menu = menuService.addRecetteToMenu(recette, jourId, repas);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateQuantiteToRecette", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateQuantiteToRecette(@RequestParam Integer recetteId, @RequestParam Integer ajout) {
        logger.debug("updateQuantiteToRecette Menu");
        menuService.updateQuantiteToRecette(recetteId, ajout);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/getByWeek", method = RequestMethod.GET)
    public ResponseEntity<Menu> getByWeek(@RequestParam Integer week, @AuthenticationPrincipal User user) {
        logger.debug("getByWeek Menu");
        Menu menu = menuService.findByWeekAndUser(week, user);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @RequestMapping(value = "/copyPrevious", method = RequestMethod.PUT)
    public ResponseEntity<Menu> copyPrevious(@RequestParam Integer weekNumber, @AuthenticationPrincipal User user) {
        logger.debug("copyPrevious weekNumber "+weekNumber);
        Menu menu = menuService.copyPrevious(weekNumber, user);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Menu>> getAll() {
        logger.debug("getAll Menu");
        List<Menu> menus = menuService.findAll();
        if(menus.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }


}
