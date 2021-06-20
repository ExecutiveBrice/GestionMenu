
import { Component, OnInit } from '@angular/core';

import { DomSanitizer } from '@angular/platform-browser';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { Ingredient } from 'src/app/models';
import { ConfigService, IngredientService, TransmissionService } from 'src/app/services';


@Component({
  selector: 'app-ingredient',
  templateUrl: './ingredient.component.html',
  styleUrls: ['./ingredient.component.css']
})

export class IngredientComponent implements OnInit {
  subscription = new Subscription()
  ingredients: Ingredient[]
  ingredient: Ingredient;
  rayons:String[]
  btnAddIngredient:String

  filternom:String


  constructor(
    public ingredientService: IngredientService,
    public configService: ConfigService,
    public router: Router,
    private transmissionService: TransmissionService,
    public sanitizer: DomSanitizer) { }


  ngOnInit() {
    this.filternom=""
    this.ingredients=[]
    console.log(this.ingredients)

    this.ingredient = new Ingredient();
    this.getAllIngredients()
    this.rayons = this.configService.getRayons();


    
    this.subscription = this.transmissionService.filterStream.subscribe(
      data => {
          console.log(data)
          this.filternom=data
      });

  }

  getAllIngredients() {
    this.ingredientService.getAll()
      .subscribe(data => {
        console.log(data);
        this.ingredients = data
      }, err => {
        console.log(err);
      });

  }

  addIngredient(ingredient: Ingredient) {

    this.ingredientService.ajout(ingredient)
      .subscribe(data => {
        console.log(data);
        this.ingredient = new Ingredient();
        this.getAllIngredients()
      }, err => {
        console.log(err);
      });

  }


  updateIngredient(ingredient: Ingredient) {
    this.ingredientService.update(ingredient)
      .subscribe(data => {
        console.log(data);
        this.getAllIngredients()
      }, err => {
        console.log(err);
      });

  }


  remove(ingredient: Ingredient) {
    this.ingredientService.delete(ingredient)
      .subscribe(data => {
        console.log(data);
        this.getAllIngredients()
      }, err => {
        console.log(err);
      });

  }


}