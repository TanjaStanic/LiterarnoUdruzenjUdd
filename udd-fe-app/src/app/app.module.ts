import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AddBookComponent } from './components/add-book/add-book.component';
import { SendToBetaComponent } from './components/send-to-beta/send-to-beta.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { HttpClientModule } from '@angular/common/http';
import { AllBooksComponent } from './components/all-books/all-books.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddBookComponent,
    SendToBetaComponent,
    HomepageComponent,
    AllBooksComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule.forRoot()
 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
