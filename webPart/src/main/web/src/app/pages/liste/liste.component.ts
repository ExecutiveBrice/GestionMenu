
import { Component, OnInit } from '@angular/core';

import { DomSanitizer } from '@angular/platform-browser';
import * as moment from 'moment';
import { Router, ActivatedRoute } from '@angular/router';
import { Ingredient, IngredientUsed } from 'src/app/models';
import { ConfigService, IngredientService, IngredientUsedService, MenuService } from 'src/app/services';


@Component({
  selector: 'app-liste',
  templateUrl: './liste.component.html',
  styleUrls: ['./liste.component.css']
})

export class ListeComponent implements OnInit {
  rayons: string[]

  lock: boolean
  listeCourse = new Map<string, IngredientUsed[]>();
  weekNumber: number
  lastday
  firstDay
  ingredients: Ingredient[]
  allIngredientsUsed:IngredientUsed[]

  constructor(
    public ingredientService: IngredientService,
    public ingredientUsedService: IngredientUsedService,
    public configService: ConfigService,
    public route: ActivatedRoute,
    public router: Router,
    public menuService: MenuService,
    public sanitizer: DomSanitizer) { }


  ngOnInit() {
    this.lock = true;
    this.getAllIngredients()
    this.rayons = this.configService.getRayons();

    this.route.params.subscribe(
      params => {
        console.log(params['week'])
        if (!isNaN(params['week'])) {
          if (parseInt(params['week']) > 52) {
            this.router.navigate(['/liste/1']);
          } else {
            this.weekNumber = parseInt(params['week'])
          }

          this.getAllIngredientsOfWeek(this.weekNumber);
          this.firstDay = moment().locale('fr').day("Lundi").week(this.weekNumber).format('D MMMM');
          this.lastday = moment().locale('fr').day("Dimanche").week(this.weekNumber).format('D MMMM');
        } else {
          this.router.navigate(['/liste/' + moment().week()]);
        }
      }
    );
  }






  getAllIngredients() {
    this.ingredients = []
    this.ingredientService.getAll()
      .subscribe(data => {
        console.log(data);
        this.ingredients = data
      }, err => {
        console.log(err);
      });

  }

  updateIngredientListe(ingredientsUsed: IngredientUsed[]) {
    var ingredientIds = []
    ingredientsUsed.forEach(ingredientUsed => {
      console.log(ingredientUsed)
      ingredientIds.push(ingredientUsed.ingredient.id)
    });
    console.log(ingredientIds)
    this.ingredients = this.ingredients.filter(ingredient => !ingredientIds.includes(ingredient.id));
    console.log(this.ingredients)

  }

  getAllIngredientsOfWeek(weekNumber: number) {
    this.ingredientUsedService.getAllIngredientsOfWeek(weekNumber)
      .subscribe(data => {
        console.log(data);
        this.allIngredientsUsed = data;
        this.listeCourse = new Map<string, IngredientUsed[]>();
        this.parseListeCourseByRayon(data, this.listeCourse)
      }, err => {
        console.log(err);
      });
  }

  parseListeCourseByRayon(ingredientsUsed: IngredientUsed[], listeCourse: Map<string, IngredientUsed[]>) {
    this.updateIngredientListe(ingredientsUsed)
    this.rayons.forEach(rayon => {
      var listeRayon = []
      ingredientsUsed.forEach(ingredientUsed => {
        if (ingredientUsed.ingredient.rayon == rayon) {
          listeRayon.push(ingredientUsed)
        }
      });

      if (listeRayon.length > 0) {
        listeCourse.set(rayon, listeRayon)
      }
    });
  }



  
  choisir(ingredient: Ingredient) {
    var thisWeek = parseInt(this.route.snapshot.paramMap.get('week'))

    var ingre = new IngredientUsed()
    ingre.ingredient = ingredient
    ingre.quantite = 1
    console.log(ingre)

    this.ingredientUsedService.addIngredientUsedToListe(ingre, thisWeek)
      .subscribe(data => {
        console.log(data);

        this.allIngredientsUsed.push(data)
        this.listeCourse = new Map<string, IngredientUsed[]>();
        this.parseListeCourseByRayon(this.allIngredientsUsed, this.listeCourse)

      }, err => {
        console.log(err);
      });
  }

  remove(ingredientUsed: IngredientUsed) {
    this.ingredientUsedService.delete(ingredientUsed)
      .subscribe(data => {
        console.log(data);
        this.getAllIngredientsOfWeek(parseInt(this.route.snapshot.paramMap.get('week')));
      }, err => {
        console.log(err);
      });
  }

  update(ingredientUsed: IngredientUsed) {
    this.ingredientUsedService.update(ingredientUsed)
      .subscribe(data => {
        console.log(data);
        this.getAllIngredientsOfWeek(parseInt(this.route.snapshot.paramMap.get('week')));
      }, err => {
        console.log(err);
      });
  }

  refreshList() {
    this.ingredientUsedService.refreshList(this.weekNumber)
      .subscribe(data => {
        console.log(data);
        this.parseListeCourseByRayon(data, this.listeCourse)
      }, err => {
        console.log(err);
      });
  }

}