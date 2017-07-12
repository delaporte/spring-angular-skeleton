import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import {LoginForm} from "./login.form";
import "rxjs/add/operator/map";
import {SignupForm} from "./signup.form";
/**
 * Created by conta on 18/05/2017.
 */

@Injectable()
export class LoginService {

  constructor(private http: Http) {
  }

  login(loginForm:LoginForm, callback) {
    let headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    });

    let credentials = 'username=' + loginForm.username + '&password=' + loginForm.password;
    if (loginForm.rememberMe){
      credentials += 'remember-me=on';
    }

    return this.http.post('/api/login', credentials, new RequestOptions({headers: headers}))
      .subscribe(callback);
  }

  getLogin() {
    return this.http.get('/api/login', {})
      .map((res) => res.json());
  }

  signUp(signUpForm: SignupForm) {
    console.log('post', signUpForm);
    let headers = new Headers({
      'Content-Type': 'application/json'
    });
    console.log('headers', headers);
    let response = this.http.post('/api/login/signup', signUpForm)
      .map((res) => res.json());

    console.log('response', response);
    return response;
  }
}
