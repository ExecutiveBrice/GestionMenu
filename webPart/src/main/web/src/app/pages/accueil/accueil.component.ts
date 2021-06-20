
import { Component, OnInit } from '@angular/core';

import { DomSanitizer } from '@angular/platform-browser';

import { Router, ActivatedRoute } from '@angular/router';
import { RecetteType } from 'src/app/models';
import { RecetteTypeService } from 'src/app/services';
import { TokenStorageService } from 'src/auth/token-storage.service';


@Component({
  selector: 'app-menu',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})

export class AccueilComponent implements OnInit {

  choosenType: string

  allTypes: RecetteType[]

  loggedIn: boolean
  userId: number
  constructor(
    public recetteTypeService: RecetteTypeService,
    public route: ActivatedRoute,
    public router: Router,
    public sanitizer: DomSanitizer,
    private tokenStorage: TokenStorageService) { }


  ngOnInit() {
    this.getAllType()
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



  chooseType(type: RecetteType) {
    this.router.navigate(['/recettes/' + type.name]);
  }


}