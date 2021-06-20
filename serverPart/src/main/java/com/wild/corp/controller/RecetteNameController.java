package com.wild.corp.controller;


import com.wild.corp.model.RecetteName;
import com.wild.corp.model.User;
import com.wild.corp.service.RecetteNameService;
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
@RequestMapping("/recetteName")
public class RecetteNameController {

    public static final Logger logger = LoggerFactory.getLogger(RecetteNameController.class);

    @Autowired
    private RecetteNameService recetteNameService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<RecetteName> add(@RequestBody RecetteName recetteName, @RequestParam String name) {
        logger.debug("add RecetteName "+name);

        recetteNameService.add(recetteName.getNom(), name);
        return new ResponseEntity<>(recetteName, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<RecetteName> update(@RequestBody RecetteName recetteName) {
        logger.debug("update RecetteName");
        recetteNameService.update(recetteName);

        return new ResponseEntity<>(recetteName, HttpStatus.OK);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<RecetteName> getById(@RequestParam String name) {
        logger.debug("getById RecetteName "+name);
        RecetteName recette = recetteNameService.findById(name).get();

        return new ResponseEntity<>(recette, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllByUser", method = RequestMethod.GET)
    public ResponseEntity<List<RecetteName>> getAllByUser(@AuthenticationPrincipal User user) {
        logger.debug("getAllByUser RecetteName");
        List<RecetteName> recetteNames = recetteNameService.findAllByUser(user);

        if(recetteNames.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recetteNames, HttpStatus.OK);
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<RecetteName>> getAll() {
        logger.debug("getAll RecetteName");
        List<RecetteName> recetteNames = recetteNameService.findAll();

        if(recetteNames.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recetteNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllByType", method = RequestMethod.GET)
    public ResponseEntity<List<RecetteName>> getAllByType(@RequestParam String name) {
        logger.debug("getAllByType RecetteName "+name);
        List<RecetteName> recetteNames = recetteNameService.findAllByType(name);

        if(recetteNames.isEmpty()) {
            return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(recetteNames, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam String name) {
        logger.debug("delete RecetteName "+name);
        recetteNameService.delete(name);

        return new ResponseEntity<>(name, HttpStatus.OK);
    }

}
