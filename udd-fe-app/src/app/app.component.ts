import {User} from './models/user';
import {UserServiceService} from './services/user-service.service';
import {ActivatedRoute} from '@angular/router';
import {AuthServiceService} from './services/auth-service.service';

import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {
  title = 'udd-fe-app';
  
  
  
  logged: boolean;
  notLogged: boolean;
  token: any;
  user : User;
  id_logged : number;
  
  constructor(private userService: UserServiceService, private route: ActivatedRoute, private auth: AuthServiceService) { }
  
  
  ngOnInit() {
	    this.token = this.auth.getJwtToken();
	    console.log('Token je ');
	    console.log(this.token);
	    if (!this.token) {
	      this.notLogged = true;
	      console.log('Niko nije ulogovan');
	    } else {
	      console.log('Neko je ulogovan');
	      this.logged = true;
	      this.userService.getLogged(this.token).subscribe(podaci => { this.pathToList(podaci); });
	     }
	  }
  pathToList(data)
  {
    this.user = data as User;
    console.log(this.user);
    this.id_logged=this.user.id;
  }
  logOutUser() {
    
    this.userService.logOut().subscribe(
    		podaci => {
    			window.location.href='http://localhost:4200/homepage'
    		},
    		err => {
    			window.location.href='http://localhost:4200/homepage'
    		});
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
    localStorage.clean();
  }
  
}
