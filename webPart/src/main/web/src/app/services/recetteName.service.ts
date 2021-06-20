import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { RecetteName, RecetteType } from '../models';

@Injectable()
export class RecetteNameService {
  apiUrl = 'recetteName';

  constructor(
    private http: HttpClient
  ) { }

  getAll() {
    return this.http.get<RecetteName[]>(this.apiUrl + '/getAll', {responseType: 'json'});
  }
  getAllByUser() {
    return this.http.get<RecetteName[]>(this.apiUrl + '/getAllByUser', {responseType: 'json'});
  }
  getAllByType(name: String) {
    let params = new HttpParams().set('name', ''+name+'');
    return this.http.get<RecetteName[]>(this.apiUrl + '/getAllByType', {params, responseType: 'json'});
  }
  
  getById(nom:string) {
    let params = new HttpParams().set('name', ''+nom+'');
    return this.http.get<RecetteName>(this.apiUrl + '/getById', {params, responseType: 'json'});
  }

  ajout(recetteName:RecetteName) {
    return this.http.post<RecetteName>(this.apiUrl + '/', recetteName, {responseType: 'json'});
  }

  delete(recetteName:RecetteName) {
    let params = new HttpParams().set('name', ''+recetteName.nom+'');
    return this.http.delete(this.apiUrl + '/', {params, responseType: 'json'});
  }

  
}
