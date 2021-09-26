import {User} from './../../models/user';
import {AuthServiceService} from './../../services/auth-service.service';
import {BookUnitServiceService} from './../../services/book-unit-service.service';
import {UserServiceService} from './../../services/user-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-books',
  templateUrl: './all-books.component.html',
  styleUrls: ['./all-books.component.sass']
})
export class AllBooksComponent implements OnInit {

	logged: boolean;
	notLogged: boolean;
	token: any;
	user : User;
	id_logged : number;
	
	books : any;
	bookUnits : any;
  constructor(private bookService: BookUnitServiceService,
		  private userService: UserServiceService,
		  private auth: AuthServiceService
		  ) { }

  ngOnInit(): void {
	  this.token = this.auth.getJwtToken();
	    console.log(this.token);
	    if (!this.token) {
	      this.notLogged = true;
	    } else {
	      this.logged = true;
	      
	     }
	//get all books
	    this.getPublishedBooks();
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
  buyBook() {
	  window.alert("Knjiga je dodana u korpu!")
  }
  downloadBook() {
	  window.alert("Knjiga se preuzima")
  }
}
