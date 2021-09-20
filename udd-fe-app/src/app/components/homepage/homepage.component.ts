import {AdvancedQuerySend} from './../../models/AdvancedQuerySend';
import {AdvancedQuery} from './../../models/AdvancedQuery';

import {BookUnit} from './../../models/book-unit';
import {User} from './../../models/user';
import { Component, OnInit } from '@angular/core';
import {ActivatedRoute,Router} from '@angular/router';
import {AuthServiceService} from './../../services/auth-service.service';
import {BookUnitServiceService} from './../../services/book-unit-service.service';
import {UserServiceService} from './../../services/user-service.service';
import {SearchServiceService} from './../../services/search-service.service';

import { IDropdownSettings } from 'ng-multiselect-dropdown';

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
	items = Array();
	
	
	//dropdown
	enumValues = [];
	  dropdownList = Array();
	  selectedItems = Array();
	  dropdownSettings = {};

	  //basic
	  basicQuery :string= "" ;
	  basicBooks : any;
	  
	  //adnvansed
	   fieldList = Array<AdvancedQuery>();
  	   fieldListSend = Array<AdvancedQuerySend>();
  	  advancedBooks : any;
  	  
  constructor(private userService: UserServiceService,
		  private auth: AuthServiceService, 
		  private bookService: BookUnitServiceService, 
		  private route: ActivatedRoute, 
		  private router : Router,
		  private searchService: SearchServiceService) {
	     this.dropdownSettings = {
                 singleSelection: true,
                 idField: 'item_id',
                 textField: 'item_text',
                 selectAllText: 'Select All',
                 unSelectAllText: 'UnSelect All',
                 allowSearchFilter: true,
               }
	  this.dropdownList = [
	                       {item_id: "all", item_text: "sva polja"},
	                       {item_id: "title", item_text: "naslov"},
	                       {item_id: "writer", item_text: "ime/prezime pisca"},
	                       {item_id: "genre", item_text: "zanr"},
	                       {item_id: "content", item_text: "tekst"}
	                     ];
  
  	}
  
  ngOnInit() {

	  //for loggeeed user
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
	    //get all books
	    this.getPublishedBooks();
	    
	    //for dropdown

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
	onItemSelect(item: any) {
	    this.selectedItems.push(item.item_id);
	  }

	  onItemDeSelect(item: any) {
	    this.selectedItems.splice(item,1);
	  }

	  
	  addField() {
		    let newM = new AdvancedQuery();
		   
		    this.fieldList.push(newM);
		  }
	  
	  onSubimitBasic() {
		    var field = "";
		    this.selectedItems.forEach(
		    		element =>{
		      console.log("SELECTED " + element);
		      field = element;
		    })
	  
		    console.log("query " + this.basicQuery);
		    this.searchService.searchBasic(field, this.basicQuery).subscribe(data=>{
		      console.log(data);
		      this.basicBooks = data;
		      this.books = [];
		      
		    },
		    err => {
		      console.log("Error occured");
		    })
	  }

	  
	  onSubmitAdvanced(){
		  console.log(this.fieldList)
		  this.fieldListSend = [];
		    this.fieldList.forEach(
		    		element =>{
					      var field;
					      element.field.forEach(el => {
					      console.log("FIELD " + el.item_id);
					      field = el.item_id;
		      });
		      
		      console.log("PHRASE " + element.isPhrase);
		      console.log("OPERATION " + element.operation);
		      console.log("QUERY " + element.query);
		      var advanced = new AdvancedQuerySend();
		      if (element.isPhrase == undefined || element.isPhrase == false) {
		        advanced.phrase = false;
		      }else{
		        advanced.phrase = true;
		      }
		      
		      advanced.query = element.query;
		      advanced.operation = element.operation;
		      advanced.field = field;
		      this.fieldListSend.push(advanced);
		    })
		    
		    console.log(this.fieldListSend);
		    this.searchService.searchAdvanced(this.fieldListSend).subscribe(data=>{
		      console.log(data);
		      this.advancedBooks = data;
		      this.fieldListSend = [];
		      this.fieldList = [];
		    },
		    err => {
		      console.log("Error occured");
		    })
	  }
}
