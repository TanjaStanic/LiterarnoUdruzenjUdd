import {AuthServiceService} from './auth-service.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {Router} from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class SearchServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private router : Router) { }
  
  searchBasic(field : any, query : any){
	    return this.http.post('http://localhost:8080/search/basic/'+field, query);
	}
  searchAdvanced(list : any){
	    return this.http.post('http://localhost:8080/search/advanced', list, {headers: this.auth.createAuthorizationTokenHeader()});
	}
}
