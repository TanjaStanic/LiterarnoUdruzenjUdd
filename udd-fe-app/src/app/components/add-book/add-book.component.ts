import {AuthServiceService} from './../../services/auth-service.service';
import {BookUnitServiceService} from './../../services/book-unit-service.service';
import {Book} from './../../models/book';
import {UserServiceService} from './../../services/user-service.service';
import {User} from './../../models/user';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.sass']
})
export class AddBookComponent implements OnInit {

	logged: boolean;
	notLogged: boolean;
	token: any;
	user : User;
	id_logged : number;
	book : Book = new Book();
	myBooks : any;
	selectedFile : any;

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
pathToList(data)
{
  console.log("in path to list");
  this.user = data as User;
  console.log(this.user);
  this.id_logged=this.user.id;
  this.getBooksWriter();
}

  addBook(){
	  console.log(this.book);
	  this.bookService.addBook(this.id_logged,this.book).subscribe(
			  data => {
				  console.log(data);
				  window.alert("Uspjesno poslani podaci o knjizi!");
				  window.location.href = 'http://localhost:4200';
			  },
			  err => {
				  
				  console.log(err);
			  }
			  );
  }
  
  getBooksWriter(){
	  this.bookService.getBooksWriter(this.id_logged).subscribe(
			  data => {
				  console.log("My books");
				  console.log(data);
				  this.myBooks = data;
			  },
			  err => {
				  
			  });
	  
  }
   uploadFile (id_book){
	   console.log("upload file function")
	   this.bookService.uploadFiles(this.selectedFile, id_book).subscribe(
			   data => {
			   	window.alert("Successfully uploaded file");
			   	window.location.href = 'http://localhost:4200/add-book';
			   });
   }
 
   onFileSelected(event){
	      this.selectedFile = <File>event.target.files[0];
	      console.log(this.selectedFile)
      
	      
	   }
   


}
