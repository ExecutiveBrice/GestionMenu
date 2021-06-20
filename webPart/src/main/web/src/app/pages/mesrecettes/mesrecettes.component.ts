
import { Component, OnInit } from '@angular/core';

import { DomSanitizer } from '@angular/platform-browser';

import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Recette } from 'src/app/models';
import { RecetteNameService, RecetteService, TransmissionService } from 'src/app/services';
import { TokenStorageService } from 'src/auth/token-storage.service';


@Component({
  selector: 'app-menu',
  templateUrl: './mesrecettes.component.html',
  styleUrls: ['./mesrecettes.component.css']
})

export class MesRecettesComponent implements OnInit {

  typesRecette: String[] = ["Mes recettes", "Toutes les recettes", "Celles des autres"]
  choosenType: string

  allRecettes: Recette[]
  recette: Recette
  loggedIn: boolean
  userId: number
  type: string

  subscription = new Subscription()
  filternom:String

  constructor(
    public recetteNameService: RecetteNameService,
    public recetteService: RecetteService,
    public route: ActivatedRoute,
    public router: Router,
    private transmissionService: TransmissionService,
    public sanitizer: DomSanitizer,
    private tokenStorage: TokenStorageService) { }


  ngOnInit() {
    this.filternom=""
    this.allRecettes = []
    this.getAllByUser()
    console.log(this.allRecettes)
    this.recette = new Recette();
    this.loggedIn = this.tokenStorage.isLoggedIn();
    this.userId = this.tokenStorage.getId();


    this.subscription = this.transmissionService.filterStream.subscribe(
      data => {
          console.log(data)
          this.filternom=data
      });
  }

  getAllByUser() {
    this.recetteService.getAllByUser()
      .subscribe(data => {
        console.log(data);
        if (data != null) {
          this.allRecettes = data
        } else {
          this.allRecettes = []
        }

      }, err => {
        console.log(err);
      });
  }


  addRecette(recette: Recette) {
    this.recetteService.ajout(recette)
      .subscribe(data => {
        console.log(data);
        this.recette = new Recette();
        this.getAllByUser()
      }, err => {
        console.log(err);
      });
  }

  remove(recette: Recette) {
    this.recetteService.delete(recette)
      .subscribe(data => {
        console.log(data);
        this.getAllByUser()
      }, err => {
        console.log(err);
      });

  }

  openRecette(recette: Recette) {
    this.router.navigate(['/recette/' + recette.id]);
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
        this.getAllByUser()

      }, err => {
        console.log(err);
      });
    }else{
    this.recetteService.addStarr(recette)
      .subscribe(data => {
        console.log(data);
        this.getAllByUser()

      }, err => {
        console.log(err);
      });
    }
  }


}
