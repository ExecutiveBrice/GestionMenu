
import { Component, OnInit } from '@angular/core';

import { DomSanitizer } from '@angular/platform-browser';

import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Recette, RecetteName, RecetteType } from 'src/app/models';
import { RecetteNameService, RecetteService, RecetteTypeService, TransmissionService } from 'src/app/services';
import { TokenStorageService } from 'src/auth/token-storage.service';


@Component({
  selector: 'app-menu',
  templateUrl: './recettes.component.html',
  styleUrls: ['./recettes.component.css']
})

export class RecettesComponent implements OnInit {


  choosenType: string
  allTypes: RecetteType[]
  allRecettesName: RecetteName[]

  loggedIn: boolean
  userId: number
  type: string

  subscription = new Subscription()
  filternom:String


  constructor(
    public recetteNameService: RecetteNameService,
    public recetteTypeService: RecetteTypeService,
    public recetteService: RecetteService,
    public route: ActivatedRoute,
    public router: Router,
    private transmissionService: TransmissionService,
    public sanitizer: DomSanitizer,
    private tokenStorage: TokenStorageService) { }


  ngOnInit() {

    this.filternom=""
    this.getAllType();
    this.route.params.subscribe(
      params => {
        console.log(params['type'])
        this.type = params['type'];
        this.getAllByType(this.type)

      }
    );

    this.loggedIn = this.tokenStorage.isLoggedIn();
    this.userId = this.tokenStorage.getId();


    
    this.subscription = this.transmissionService.filterStream.subscribe(
      data => {
          console.log(data)
          this.filternom=data
      });
  }


  isStarring(recette: Recette): boolean{
    console.log(recette.stars.some(star => star.id == this.userId))
    return recette.stars.some(star => star.id == this.userId)
  }


  starring(recette:Recette){

    if(this.isStarring(recette)){
      this.recetteService.removeStarr(recette)
      .subscribe(data => {
        console.log(data);
        this.getAllByType(this.type)

      }, err => {
        console.log(err);
      });
    }else{
    this.recetteService.addStarr(recette)
      .subscribe(data => {
        console.log(data);
        this.getAllByType(this.type)

      }, err => {
        console.log(err);
      });
    }
  }


  changeType(typeName: string){
    this.router.navigate(['/recettes/' + typeName]);
    this.type = typeName;
    this.getAllByType(typeName)
  }

  getAllByType(typeName: String) {
    this.allRecettesName = []
    this.recetteNameService.getAllByType(typeName)
      .subscribe(data => {
        console.log(data);
        if (data != null) {
          this.allRecettesName = data
        }
        this.allRecettesName.forEach(recetteName => {
          this.recetteService.getAllByName(recetteName.nom)
            .subscribe(data => {
              console.log(data);
              if (data != null) {
                recetteName.recettes = data
              }
            }, err => {
              console.log(err);
            });
        });
      }, err => {
        console.log(err);
      });
  }



  getAllType() {
    this.recetteTypeService.getAll()
      .subscribe(data => {
        console.log(data);
        if (data != null) {
          this.allTypes = data
        } else {
          this.allTypes = []
        }

      }, err => {
        console.log(err);
      });
  }


  addRecette(nom: string) {
    let recette = new Recette();
    recette.recetteName = new RecetteName();
    recette.recetteName.type = new RecetteType();
    recette.recetteName.type.name = this.type
    recette.recetteName.nom = nom

    this.recetteService.ajout(recette)
      .subscribe(data => {
        console.log(data);
        this.getAllByType(this.type)
      }, err => {
        console.log(err);
      });
  }

  remove(recette: Recette) {
    this.recetteService.delete(recette)
      .subscribe(data => {
        console.log(data);
        this.getAllByType(this.type)
      }, err => {
        console.log(err);
      });

  }

  openRecette(recette: Recette) {
    this.router.navigate(['/recette/' + recette.id]);
  }


}