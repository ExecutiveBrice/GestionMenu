import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";


@Injectable()
export class ConfigService {
  apiUrl = 'config';

  constructor(
    private http: HttpClient
  ) { }

  getParams() {
    return this.http.get<Map<string, string>>(this.apiUrl + '/getParams', { responseType: 'json'});
  }


  getRayons() {
    return ["Sec", "Frais", "Froid", "Légume", "Liquide", "Poisson", "Viande", "Conserve", "Autre"]
  }



}
