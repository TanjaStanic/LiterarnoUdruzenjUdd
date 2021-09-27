import {AuthServiceService} from './auth-service.service';
import {Book} from './../models/book';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class BookUnitServiceService {

	
	book : Book;


  constructor(private http: HttpClient, private auth: AuthServiceService, private router : Router) { }
  
  addBook(id: number, book : Book) {
      console.log('in add book service : ' + id );

      return this.http.post('http://localhost:8080/book/addBookToWriter/'+id, book, {responseType: 'text'});
     }
  
  getBooksWriter(id: number) {
	  console.log("in get books writer service id: "+id);
      return this.http.get('http://localhost:8080/book/getAllBooks/'+id, {headers: this.auth.createAuthorizationTokenHeader()});
  }
  getBooksEditor(id: number) {
	  console.log("in get books editor service id: "+id);
      return this.http.get('http://localhost:8080/book/getAllBooksEditor/'+id, {headers: this.auth.createAuthorizationTokenHeader()});
  }
  acceptBook(id: number) {
      console.log('in activate book service : ' + id );

      return this.http.get('http://localhost:8080/book/acceptBook/'+id, {responseType: 'text'});
     }
  rejectBook(id: number) {
      console.log('in activate book service : ' + id );

      return this.http.get('http://localhost:8080/book/rejectBook/'+id, {responseType: 'text'});
     }
  uploadFiles(file, id:number) {
	  
	    console.log(file);
	    const formData = new FormData();

	      formData.append('file', file);
	    console.log(formData);
	    return this.http.post('http://localhost:8080/book/uploadFile/'+id, formData, {responseType: 'text'});
	  }
  
  publishBook(id: number) {
	  
	  console.log("In upload book unit service");
	  return this.http.get('http://localhost:8080/bookUnit/publishBook/'+id, {responseType: 'text'});
  }
  
  getPublishedBooks(){
	  return this.http.get('http://localhost:8080/bookUnit/getBookUnits', {headers: this.auth.createAuthorizationTokenHeader()});
  }

 //admin controls
	reindexBooks(){
		 return this.http.get('http://localhost:8080/bookUnit/reindex',{responseType: 'text'});
	}
	reindexBetaReaders(){
		return this.http.get('http://localhost:8080/betaReader/reindex',{responseType: 'text'});
	}
	deleteBooks(){
		 return this.http.get('http://localhost:8080/bookUnit/deleteAll',{responseType: 'text'});
	}
	deleteBetaReaders(){
		return this.http.get('http://localhost:8080/betaReader/deleteAll',{responseType: 'text'});
	}

}
