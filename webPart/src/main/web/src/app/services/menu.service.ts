import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Jour, Menu, Recette, RecetteUsed } from '../models';

@Injectable()
export class MenuService {
  apiUrl = 'menu';

  constructor(
    private http: HttpClient
  ) { }

  getAll() {
    return this.http.get<Menu[]>(this.apiUrl + '/getAll', {responseType: 'json'});
  }
  
  getByWeek(week:number) {
    let params = new HttpParams().set('week', ''+week+'');
    return this.http.get<Menu>(this.apiUrl + '/getByWeek', {params, responseType: 'json'});
  }

  getById(id:number) {
    let params = new HttpParams().set('id', ''+id+'');
    return this.http.get<Menu>(this.apiUrl + '/getById', {params, responseType: 'json'});
  }
  
  ajout(creneau:Menu) {
    return this.http.post<Menu>(this.apiUrl + '/', creneau, {responseType: 'json'});
  }

  addRecetteToMenu(recette:Recette,jour: Jour, repas: string) {
    let params = new HttpParams().set('jourId', ''+jour.id+'').set('repas', ''+repas+'');
    return this.http.put<Menu>(this.apiUrl + '/addRecetteToMenu', recette, {params, responseType: 'json'});
  }

  updateQuantiteToRecette(recetteUsed:RecetteUsed,ajout: number) {
    let params = new HttpParams().set('recetteId', ''+recetteUsed.id+'').set('ajout', ''+ajout+'');

    return this.http.put<Menu>(this.apiUrl + '/updateQuantiteToRecette', {}, {params, responseType: 'json'});
  }
  
  copyPrevious(weekNumber:number){
    let params = new HttpParams().set('weekNumber', ''+weekNumber+'');
    return this.http.put<Menu>(this.apiUrl + '/copyPrevious', {}, {params, responseType: 'json'});
  }


  


  delete(creneau:Menu) {
    let params = new HttpParams().set('id', ''+creneau.id+'');
    return this.http.delete(this.apiUrl + '/', {params, responseType: 'json'});
  }
  
}
