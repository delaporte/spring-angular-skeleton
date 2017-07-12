import {Component, OnInit} from '@angular/core';
import {SignupForm} from "./signup.form";
import {Http, Headers, RequestOptions} from "@angular/http";
import {LoginService} from "./login.service";
/**
 * Created by conta on 18/05/2017.
 */

@Component({
  selector: 'signup',
  templateUrl: './signup.component.html'
})
export class SignupComponent {
  private signupForm:SignupForm = new SignupForm();

  constructor(private loginService:LoginService) {
  }

  signUp():void {
    console.log('signup');
    this.loginService.signUp(this.signupForm).subscribe(response => console.log(response));
  }
}
