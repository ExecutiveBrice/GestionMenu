import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { RecetteType } from '../models';

@Injectable()
export class RecetteTypeService {
  apiUrl = 'recetteType';

  constructor(
    private http: HttpClient
  ) { }

  
  getAll() {
    return this.http.get<RecetteType[]>(this.apiUrl + '/getAll', {responseType: 'json'});
  }

  getById(nom:string) {
    let params = new HttpParams().set('nom', ''+nom+'');
    return this.http.get<RecetteType>(this.apiUrl + '/getById', {params, responseType: 'json'});
  }

  ajout(type:RecetteType) {
    return this.http.post<RecetteType>(this.apiUrl + '/', type, {responseType: 'json'});
  }


  delete(type:RecetteType) {
    let params = new HttpParams().set('nom', ''+type.name+'');
    return this.http.delete(this.apiUrl + '/', {params, responseType: 'json'});
  }

  
}
