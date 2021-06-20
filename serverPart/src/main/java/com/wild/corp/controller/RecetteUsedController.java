package com.wild.corp.controller;

import com.wild.corp.model.RecetteUsed;
import com.wild.corp.service.RecetteUsedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recetteUsed")
public class RecetteUsedController {

    public static final Logger logger = LoggerFactory.getLogger(RecetteUsedController.class);

    @Autowired
    private RecetteUsedService recetteUsedService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<RecetteUsed> add(@RequestBody RecetteUsed recetteUsed) {
        logger.debug("add RecetteUsed");
        recetteUsedService.add(recetteUsed);

        return new ResponseEntity<>(recetteUsed, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<RecetteUsed> update(@RequestBody RecetteUsed recetteUsed) {
        logger.debug("update RecetteUsed");
        recetteUsedService.update(recetteUsed);

        return new ResponseEntity<>(recetteUsed, HttpStatus.OK);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<RecetteUsed> getById(@RequestParam Integer recetteId) {
        logger.debug("getById recetteUsed");
        RecetteUsed recetteUsed = recetteUsedService.findById(recetteId);

        return new ResponseEntity<>(recetteUsed, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteUsed(@RequestParam Integer id) {
        logger.debug("deleteUsed Recette");
        recetteUsedService.delete(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
