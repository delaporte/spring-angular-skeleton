import {Routes} from '@angular/router';

import {HomeComponent} from './home/home.component';
import {SignupComponent} from "./login/signup.component";
import {LoginComponent} from "./login/login.component";

export const rootRouterConfig: Routes = [
  {path: '', redirectTo: 'index', pathMatch: 'full'},
  {
    path: 'index', component: HomeComponent,
    children: [
      {path: '', component: LoginComponent},
      {path: ':signup', component: SignupComponent}
    ]
  },
];

