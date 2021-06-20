import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { IngredientUsed } from '../models';

@Injectable()
export class IngredientUsedService {
  apiUrl = 'ingredientUsed';

  constructor(
    private http: HttpClient
  ) { }

  getAll() {
    return this.http.get<IngredientUsed[]>(this.apiUrl + '/getAllUsed', {responseType: 'json'});
  }

  getAllIngredientsOfWeek(week: number){
    let params = new HttpParams().set('weekNumber', ''+week+'');
    return this.http.get<IngredientUsed[]>(this.apiUrl + '/getAllIngredientsOfWeek', {params, responseType: 'json'});
  }

  addIngredientUsedToListe(ingredientUsed:IngredientUsed, week: number) {
    let params = new HttpParams().set('weekNumber', ''+week+'');
    return this.http.post<IngredientUsed>(this.apiUrl + '/addIngredientUsedToListe', ingredientUsed, {params, responseType: 'json'});
  }

  getById(id:number) {
    let params = new HttpParams().set('id', ''+id+'');
    return this.http.get<IngredientUsed>(this.apiUrl + '/getById', {params, responseType: 'json'});
  }

  ajout(ingredientUsed:IngredientUsed) {
    return this.http.post<IngredientUsed>(this.apiUrl + '/', ingredientUsed, {responseType: 'json'});
  }

  update(ingredientUsed:IngredientUsed) {
    return this.http.put<IngredientUsed>(this.apiUrl + '/', ingredientUsed, {responseType: 'json'});
  }

  delete(ingredient:IngredientUsed) {
    let params = new HttpParams().set('id', ''+ingredient.id+'');
    return this.http.delete(this.apiUrl + '/', {params, responseType: 'json'});
  }

  refreshList(weekNumber:number){
    let params = new HttpParams().set('weekNumber', ''+weekNumber+'');
    return this.http.put<IngredientUsed[]>(this.apiUrl + '/refreshList', {}, {params, responseType: 'json'});
  }
}
