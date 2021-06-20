import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Ingredient, Recette } from '../models';

@Injectable()
export class RecetteService {
  apiUrl = 'recette';

  constructor(
    private http: HttpClient
  ) { }

  
  getAll() {
    return this.http.get<Recette[]>(this.apiUrl + '/getAll', {responseType: 'json'});
  }

  getById(id:number) {
    let params = new HttpParams().set('recetteId', ''+id+'');
    return this.http.get<Recette>(this.apiUrl + '/getById', {params, responseType: 'json'});
  }

  addStarr(recette:Recette) {
    return this.http.put<Recette>(this.apiUrl + '/addStarr', recette, {responseType: 'json'});
  }

  removeStarr(recette:Recette) {
    return this.http.put<Recette>(this.apiUrl + '/removeStarr', recette, {responseType: 'json'});
  }
  
  getAllByUser() {
    return this.http.get<Recette[]>(this.apiUrl + '/getAllByUser', {responseType: 'json'});
  }

  getAllByType(type: String) {
    let params = new HttpParams().set('type', ''+type+'');
    return this.http.get<Recette[]>(this.apiUrl + '/getAllByType', {params, responseType: 'json'});
  }

  getAllByTypeAndStar() {
    return this.http.get<Map<string, Recette[]>>(this.apiUrl + '/getAllByTypeAndStar', {responseType: 'json'});
  }

  getAllByName(name: String) {
    let params = new HttpParams().set('name', ''+name+'');
    return this.http.get<Recette[]>(this.apiUrl + '/getAllByName', {params, responseType: 'json'});
  }

  ajout(recette:Recette) {
    return this.http.post<Recette>(this.apiUrl + '/', recette, {responseType: 'json'});
  }

  update(recette:Recette) {
    return this.http.put<Recette>(this.apiUrl + '/', recette, {responseType: 'json'});
  }

  addIngredientToRecette(recette:Recette,ingredient: Ingredient) {
    let params = new HttpParams().set('ingredientId', ''+ingredient.id+'');

    return this.http.put<Recette>(this.apiUrl + '/addIngredientToRecette', recette, {params, responseType: 'json'});
  }

  delete(recette:Recette) {
    let params = new HttpParams().set('id', ''+recette.id+'');
    return this.http.delete(this.apiUrl + '/', {params, responseType: 'json'});
  }
  
}
