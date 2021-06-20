
import { Component, OnInit } from '@angular/core';

import { DomSanitizer } from '@angular/platform-browser';


import { Router, ActivatedRoute } from '@angular/router';
import { Etape, Ingredient, IngredientUsed, Recette } from 'src/app/models';
import { IngredientUsedService, IngredientService, RecetteService, ConfigService } from 'src/app/services';
import { TokenStorageService } from 'src/auth/token-storage.service';
import { ImageCroppedEvent } from 'ngx-image-cropper';


@Component({
  selector: 'app-menu',
  templateUrl: './recette.component.html',
  styleUrls: ['./recette.component.css']
})

export class RecetteComponent implements OnInit {
  recettes: Recette[]
  recette: Recette
  ingredients: Ingredient[]
  lock: boolean
  rayons: string[]
  loggedIn: boolean
  userId: number

  quantite: number;

  constructor(
    public recetteService: RecetteService,
    public ingredientUsedService: IngredientUsedService,
    public configService: ConfigService,
    public ingredientService: IngredientService,
    public route: ActivatedRoute,
    public router: Router,
    public sanitizer: DomSanitizer,
    private tokenStorage: TokenStorageService) { }

  async ngOnInit() {
    this.quantite = 1
    this.lock = true;
    this.recette = new Recette();
    this.rayons = this.configService.getRayons();
    this.loggedIn = this.tokenStorage.isLoggedIn();
    this.userId = this.tokenStorage.getId();


    await this.getRecette(parseInt(this.route.snapshot.paramMap.get('id')));


    console.log(this.route.snapshot.paramMap.get('qt'))




    this.route.params.subscribe(
      params => {
        console.log(params['qt'])

        if (!isNaN(params['qt'])) {
          if (parseInt(params['qt']) < 1) {
            this.router.navigate(['/recette/' + parseInt(params['id']) + '/1']);
          } else {
            this.quantite = parseInt(params['qt'])
          }
        }
      }
    );


  }

  imageChangedEvent: any = '';
  croppedImage: any = '';

  fileChangeEvent(event: any): void {
    this.spinner(true)
    console.log("fileChangeEvent")
    this.imageChangedEvent = event;
  }
  imageCropped(event: ImageCroppedEvent) {
    this.spinner(true)
    console.log(event)
    this.croppedImage = event.base64;
    this.spinner(false)
  }
  imageLoaded() {
    /* show cropper */
  }
  cropperReady() {
    /* cropper ready */
  }
  loadImageFailed() {
    /* show message */
  }
  uploadImage(croppedImage: any) {

    this.recette.image = croppedImage
    this.updateRecette()
  }


  async getRecette(idRecette: number) {
    this.recetteService.getById(idRecette)
      .subscribe(data => {
        console.log(data);
        this.recette = data
        this.getAllIngredients()
      }, err => {
        console.log(err);
      });
  }


  isStarring(): boolean {
    console.log(this.recette.stars.some(star => star.id == this.userId))
    return this.recette.stars.some(star => star.id == this.userId)
  }

  starring() {

    if (this.isStarring()) {
      this.recetteService.removeStarr(this.recette)
        .subscribe(data => {
          console.log(data);
          this.recette = data
        }, err => {
          console.log(err);
        });
    } else {
      this.recetteService.addStarr(this.recette)
        .subscribe(data => {
          console.log(data);
          this.recette = data
        }, err => {
          console.log(err);
        });
    }
  }


  getAllIngredients() {
    this.ingredientService.getAll()
      .subscribe(data => {
        console.log(data);
        this.ingredients = data;
        this.updateIngredientListe()
      }, err => {
        console.log(err);
      });
  }

  updateIngredientListe() {
    var ingredientIds = []
    this.recette.ingredientsUsed.forEach(ingredientUsed => {
      console.log(ingredientUsed)
      ingredientIds.push(ingredientUsed.ingredient.id)
    });
    console.log(ingredientIds)
    this.ingredients = this.ingredients.filter(ingredient => !ingredientIds.includes(ingredient.id));
    console.log(this.ingredients)

  }

  remove(ingredientUsed: IngredientUsed) {
    this.ingredientUsedService.delete(ingredientUsed)
      .subscribe(data => {
        console.log(data);
        this.ingredients.push(ingredientUsed.ingredient)
        this.recette.ingredientsUsed = this.recette.ingredientsUsed.filter(item => ![ingredientUsed].includes(item))
        this.updateIngredientListe()
      }, err => {
        console.log(err);
      });
    this.updateRecette()
  }

  choisir(ingredient: Ingredient) {
    var ingre = new IngredientUsed()
    ingre.ingredient = ingredient
    ingre.quantite = 10
    this.recette.ingredientsUsed.push(ingre);

    this.updateRecette()
  }


  async onItemDrop(e: any, etape: Etape) {
    // Get the dropped data here
    //this.droppedItems.push(e.dragData);
    console.log(e.dragData)
    console.log(etape)
    await this.moveEtape(e.dragData, etape)
    this.updateRecette()
  }


  async moveEtape(etapeToPlace: Etape, etapeToReplace: Etape) {

    this.recette.etapes.forEach(etape => {
      if (etape.id == etapeToPlace.id) {
        etape.etape = etapeToReplace.etape
      }
      if (etape.id == etapeToReplace.id) {
        etape.etape = etapeToPlace.etape
      }

    })

  }

  addEtape() {
    var lastNumber = 0;
    this.recette.etapes.forEach(etape => {
      if (etape.etape > lastNumber) {
        lastNumber = etape.etape
      }
    })

    let etape = new Etape(lastNumber + 1);
    etape.description = "Etape " + (lastNumber + 1);
    this.recette.etapes.push(etape)

    this.updateRecette()
  }

  updateRecette() {
    this.spinner(true);
    this.recetteService.update(this.recette)
      .subscribe(data => {
        console.log(data);
        this.recette = data
        this.getAllIngredients()
        this.spinner(false);
      }, err => {
        console.log(err);
        this.spinner(false);
      });
  }

  spinner(visible: boolean) {
    if (visible) {
      document.getElementById("overlay").style.display = "block";
    } else {
      document.getElementById("overlay").style.display = "none";
    }
  }


}