import {AuthServiceService} from './auth-service.service';
import {User} from './../models/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

	user : User;
			
	constructor(private http: HttpClient, private auth: AuthServiceService, private router : Router) {
	}
   
	loginUser(user) {
        console.log('User login service');

        return this.http.post('http://localhost:8080/auth/login', user, {responseType: 'text'});
       }
    getLogged(token: string) {
        console.log("token: " + token);
        return this.http.get('http://localhost:8080/auth/userprofile/'+token, {headers: this.auth.createAuthorizationTokenHeader()});
        }
    logOut() {
        window.sessionStorage.clear();
        return this.http.get('http://localhost:8080/auth/logout', {headers: this.auth.createAuthorizationTokenHeader()});
         }

}
