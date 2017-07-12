import {Component, OnInit} from '@angular/core';
import {LoginForm} from "./login.form";
import {Http, Headers, RequestOptions} from "@angular/http";
import {LoginService} from "./login.service";
/**
 * Created by conta on 18/05/2017.
 */

@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit{
  private loginForm:LoginForm = new LoginForm();
  private user;

  constructor(private loginService:LoginService) {
  }

  ngOnInit(): void {
    this.isLogged();
  }

  login(): void {
    this.loginService.login(this.loginForm, res => this.isLogged());
  }

  isLogged(): void {
    this.loginService.getLogin()
      .subscribe(user => this.user = user);
  }
}
