import {Book} from './../../models/book';
import {User} from './../../models/user';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import {AuthServiceService} from './../../services/auth-service.service';
import {BookUnitServiceService} from './../../services/book-unit-service.service';
import {UserServiceService} from './../../services/user-service.service';

@Component({
  selector: 'app-send-to-beta',
  templateUrl: './send-to-beta.component.html',
  styleUrls: ['./send-to-beta.component.sass']
})
export class SendToBetaComponent implements OnInit {

	
	logged: boolean;
	notLogged: boolean;
	token: any;
	user : User;
	id_logged : number;
	book : Book = new Book();
	myBooks : any;
  constructor(private userService: UserServiceService,private auth: AuthServiceService, private bookService: BookUnitServiceService, private route: ActivatedRoute, private router : Router) { }

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
	pathToList(data){
		console.log("in path to list");
		this.user = data as User;
		console.log(this.user);
		this.id_logged=this.user.id;
		this.getBooksEditor();
	}
	
	getBooksEditor(){
		  this.bookService.getBooksEditor(this.id_logged).subscribe(
				  data => {
					  console.log("My books");
					  console.log(data);
					  this.myBooks = data;
				  },
				  err => {
					  
				  });
		  
	  }

	activateBook(id_book) {
		console.log("in activate book funkcition : " + id_book);
		this.bookService.activateBook(id_book).subscribe(
				data => {
					console.log("activated");
					window.alert("Knjiga je odobrena");
					window.location.href = 'http://localhost:4200/betareaders';
				});
	}
	rejectBook(id_book) {
		console.log("in reject book funkcition : " + id_book);
		this.bookService.rejectBook(id_book).subscribe(
				data => {
					console.log("reject");
					window.alert("Knjiga je odbijena");
					window.location.href = 'http://localhost:4200/betareaders';
				});
	}
	
	publishBookUnit(id_book){
		this.bookService.publishBook(id_book).subscribe(
				data=> {
					window.alert("Uspjesno objavljena knjiga!");
					window.location.href = 'http://localhost:4200/betareaders';
				},
				err => {
					console.log("error! ");
				});
		
	}
}
