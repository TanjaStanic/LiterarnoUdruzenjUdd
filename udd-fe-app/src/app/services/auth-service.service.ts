import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

	TOKEN_KEY : string;
	constructor(private http: HttpClient) {this.TOKEN_KEY="jwtToken";}
	
		 getJwtToken() {
		    return localStorage.getItem(this.TOKEN_KEY);
	}

	setJwtToken(token : any) {
		localStorage.setItem(this.TOKEN_KEY, token);
	}

	removeJwtToken() {
		localStorage.removeItem(this.TOKEN_KEY);
	}

	createAuthorizationTokenHeader() : any{
		var token = this.getJwtToken();
		if (token) {
			return {
		            "Authorization": "Bearer " + token,
		            'Content-Type': 'application/json'
		          };
		} else {
		          return {
		            'Content-Type': 'application/json'
		          };
		      }
		  }
}
