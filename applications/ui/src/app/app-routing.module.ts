import { NgModule } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import { KeyComponent } from './components/key/key.component';

export const routes: Routes = [
  // {
  //   path: '',
  //   pathMatch: 'full',
  //   redirectTo: 'home'
  // },
  // {
  //   path: 'home',
  //   component: KeyComponent
  // }
  {
    path: '',
    component: KeyComponent
  },
  {
    path: 'home',
    pathMatch: 'full',
    redirectTo: ''
  }
]
@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
