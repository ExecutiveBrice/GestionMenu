import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { RecetteUsed } from '../models';

@Injectable()
export class RecetteUsedService {
  apiUrl = 'recetteUsed';

  constructor(
    private http: HttpClient
  ) { }

  getAll() {
    return this.http.get<RecetteUsed[]>(this.apiUrl + '/getAll', {responseType: 'json'});
  }

  getById(id:number) {
    let params = new HttpParams().set('recetteId', ''+id+'');
    return this.http.get<RecetteUsed>(this.apiUrl + '/getById', {params, responseType: 'json'});
  }

  ajout(recette:RecetteUsed) {
    return this.http.post<RecetteUsed>(this.apiUrl + '/', recette, {responseType: 'json'});
  }

  update(recette:RecetteUsed) {
    return this.http.put<RecetteUsed>(this.apiUrl + '/', recette, {responseType: 'json'});
  }

  delete(recette:RecetteUsed) {
    let params = new HttpParams().set('id', ''+recette.id+'');
    return this.http.delete(this.apiUrl + '/', {params, responseType: 'json'});
  }
  
}
