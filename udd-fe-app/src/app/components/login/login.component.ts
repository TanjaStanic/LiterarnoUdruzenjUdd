import { Observable } from 'rxjs';

import {Router} from '@angular/router';
import {ActivatedRoute} from '@angular/router';

import {UserServiceService} from './../../services/user-service.service';
import {AuthServiceService} from './../../services/auth-service.service';
import {User} from './../../models/user';
import {UserToken} from './../../models/user-token';

import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

export const TOKEN = 'LoggedInUser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

	message : string;
	user : User = new User();
	htmlStr: string;
	isLoggedIn = false;
	id : string;
	logged : any
	token : any;
	
	constructor(private userService: UserServiceService, private route: ActivatedRoute, private auth : AuthServiceService, private router : Router) { }

	ngOnInit(): void {
	}

	loginUser(){
		
		console.log("Login: " + this.user.username + " pass: " + this.user.password);
	          this.userService.loginUser(this.user).subscribe(
	        		  (podaci : any)  => { 
	        			  console.log(podaci);
	        			  this.checkUser(podaci);
	          				} , 
	        		  			err => {this.handleAuthError(err);
	        		  			});    
	     
	}
	checkUser(logged : any) {
        let user_token= logged as UserToken;
        if(user_token.accessToken == 'error') {
          this.htmlStr = 'The e-mail or password is not correct.';
        } else {
          this.auth.setJwtToken(logged);
          console.log(user_token.accessToken);
          console.log("prije getLogged");
          this.userService.getLogged(logged).subscribe(podaci => {
              console.log("u getLogged");
              console.log(podaci)
              var currentUser=podaci as User; 
              console.log("cuvam u json currentusera: ");

              
              localStorage.setItem('user', JSON.stringify(currentUser));
              window.location.href = 'http://localhost:4200/homepage';
             
     });
        }
	}

	  handleAuthError(err: HttpErrorResponse) {
		  console.log(err.status);
	    if (err.status === 404) {
	      alert('Entered email is not valid!');
	    }
	    if (err.status === 400) {
		      alert('Bed request!');
		}

	  }
}
