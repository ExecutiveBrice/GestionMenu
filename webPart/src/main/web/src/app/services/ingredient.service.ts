import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Ingredient } from '../models';

@Injectable()
export class IngredientService {
  apiUrl = 'ingredient';

  constructor(
    private http: HttpClient
  ) { }

  getAll() {
    return this.http.get<Ingredient[]>(this.apiUrl + '/getAll', {responseType: 'json'});
  }

  getById(id:number) {
    let params = new HttpParams().set('id', ''+id+'');
    return this.http.get<Ingredient>(this.apiUrl + '/getById', {params, responseType: 'json'});
  }
  
  ajout(ingredientUsed:Ingredient) {
    return this.http.post<Ingredient>(this.apiUrl + '/', ingredientUsed, {responseType: 'json'});
  }
  
  update(ingredientUsed:Ingredient) {
    return this.http.put<Ingredient>(this.apiUrl + '/', ingredientUsed, {responseType: 'json'});
  }

  delete(ingredient:Ingredient) {
    let params = new HttpParams().set('id', ''+ingredient.id+'');
    return this.http.delete(this.apiUrl + '/delete', {params, responseType: 'json'});
  }

}
