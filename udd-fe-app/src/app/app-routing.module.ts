import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AddBookComponent } from './components/add-book/add-book.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { SendToBetaComponent } from './components/send-to-beta/send-to-beta.component';


const routes: Routes = [
                        {
      path: 'login',
      component: LoginComponent
    },{
        path: 'add-book',
        component: AddBookComponent
      },
     {
          path: 'homepage',
          component: HomepageComponent
        },
     	{
            path: 'betareaders',
            component: SendToBetaComponent
          }              ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
