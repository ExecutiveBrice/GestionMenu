
import { Component, OnInit } from '@angular/core';

import { DomSanitizer } from '@angular/platform-browser';
import * as moment from 'moment';
import { Router, ActivatedRoute } from '@angular/router';
import { ConfigService, MenuService, RecetteService, RecetteTypeService, RecetteUsedService } from 'src/app/services';
import { Jour, Menu, Recette, RecetteType, RecetteUsed } from 'src/app/models';
import { TokenStorageService } from 'src/auth/token-storage.service';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})

export class MenuComponent implements OnInit {
  lock: boolean
  recettesMap: Map<string, Recette[]>;
  recetteTypes: RecetteType[];
  menu: Menu
  weekNumber
  firstDay
  lastday
  loggedIn: boolean
  userId: number


  constructor(
    public configService: ConfigService,
    public recetteTypeService: RecetteTypeService,
    public recetteService: RecetteService,
    public recetteUsedService: RecetteUsedService,
    public menuService: MenuService,
    public route: ActivatedRoute,
    public router: Router,
    private tokenStorage: TokenStorageService,
    public sanitizer: DomSanitizer) { }


  ngOnInit() {
    this.lock = true;
    this.loggedIn = this.tokenStorage.isLoggedIn();
    this.userId = this.tokenStorage.getId();
    this.route.params.subscribe(
      params => {
        console.log(params['week'])

        if (!isNaN(params['week'])) {
          if (parseInt(params['week']) > 52) {
            this.router.navigate(['/menu/1']);
          } else {
            this.weekNumber = parseInt(params['week'])
          }
          this.getByWeek(this.weekNumber);
          this.firstDay = moment().locale('fr').day("Lundi").week(this.weekNumber).format('D MMMM');
          this.lastday = moment().locale('fr').day("Dimanche").week(this.weekNumber).format('D MMMM');
        } else {
          this.router.navigate(['/menu/' + moment().week()]);
        }
      }
    );




    this.getAllRecetttes();
  }

  onItemDrop(e: any, jour: Jour, repas: string) {
    // Get the dropped data here
    //this.droppedItems.push(e.dragData);
    console.log(e.dragData)
    if (!this.lock) {
      this.remove(e.dragData)
      this.choisir(e.dragData.recette, jour, repas)
    }
  }


  getAllRecetttes() {

    this.recettesMap = new Map<string, Recette[]>();

    this.recetteTypeService.getAll()
      .subscribe(data => {
        console.log(data);
        this.recetteTypes = data
      }, err => {
        console.log(err);
      });


    this.recetteService.getAllByTypeAndStar()
      .subscribe(data => {
        console.log(data);
        this.recettesMap = data
      }, err => {
        console.log(err);
      });


  }



  choisir(recette: Recette, jour: Jour, repas: string) {

    let recetteUsedList: RecetteUsed[] = jour["recettes" + repas]
    if (recetteUsedList.some(recetteUsed => recetteUsed.recette.id == recette.id)) {
      this.plus(recetteUsedList.find(recetteUsed => recetteUsed.recette.id == recette.id))
    } else {
      this.menuService.addRecetteToMenu(recette, jour, repas)
        .subscribe(data => {
          console.log(data);
          this.getByWeek(parseInt(this.route.snapshot.paramMap.get('week')));
        }, err => {
          console.log(err);
        });
    }

  }


  remove(recetteUsed: RecetteUsed) {
    this.recetteUsedService.delete(recetteUsed)
      .subscribe(data => {
        console.log(data);
        this.getByWeek(parseInt(this.route.snapshot.paramMap.get('week')));
      }, err => {
        console.log(err);
      });
  }


  minus(recetteUsed: RecetteUsed) {
    if (recetteUsed.quantite > 0) {
      recetteUsed.quantite--
    }

    this.menuService.updateQuantiteToRecette(recetteUsed, -1)
      .subscribe(data => {
        console.log(data);
      }, err => {
        console.log(err);
      });
  }


  plus(recetteUsed: RecetteUsed) {
    recetteUsed.quantite++
    this.menuService.updateQuantiteToRecette(recetteUsed, 1)
      .subscribe(data => {
        console.log(data);
      }, err => {
        console.log(err);
      });
  }

  getByWeek(weekNumber: number) {
    this.menuService.getByWeek(weekNumber)
      .subscribe(data => {
        console.log(data);
        if (data == null) {
          this.menu = new Menu(weekNumber)
          this.menuService.ajout(this.menu)
            .subscribe(data => {
              console.log(data);
              this.menu = data
            }, err => {
              console.log(err);
            });
        } else {
          this.menu = data
        }
      }, err => {
        console.log(err);
      });
  }

  openRecette(recette: Recette) {
    this.router.navigate(['/recette/' + recette.id]);
  }

  copyPrevious() {
    this.menuService.copyPrevious(this.weekNumber)
      .subscribe(data => {
        console.log(data);
        this.menu = data
      }, err => {
        console.log(err);
      });
  }

}