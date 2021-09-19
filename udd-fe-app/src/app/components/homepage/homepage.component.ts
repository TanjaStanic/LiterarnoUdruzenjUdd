import {BookUnit} from './../../models/book-unit';
import {User} from './../../models/user';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import {AuthServiceService} from './../../services/auth-service.service';
import {BookUnitServiceService} from './../../services/book-unit-service.service';
import {UserServiceService} from './../../services/user-service.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.sass']
})
export class HomepageComponent implements OnInit {
	logged: boolean;
	notLogged: boolean;
	token: any;
	user : User;
	id_logged : number;
	bookUnits : any;
	books : any;

  constructor(private userService: UserServiceService,private auth: AuthServiceService, private bookService: BookUnitServiceService, 
		  private route: ActivatedRoute, private router : Router) {


  
  }

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
	    this.getPublishedBooks();
	    
	  }
	pathToList(data){
		console.log("in path to list");
		this.user = data as User;
		console.log(this.user);
		this.id_logged=this.user.id;
	}
	getPublishedBooks(){
		  this.bookService.getPublishedBooks().subscribe(
				  data => {
					  console.log("My books");
					  console.log(data);
					  this.bookUnits = data;
					  this.books = this.bookUnits.content;
				  },
				  err => {
					  console.log("error: " + err);
				  });
		  
	  }


}
