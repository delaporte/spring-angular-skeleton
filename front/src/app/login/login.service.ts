import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import {LoginForm} from "./login-form";
import "rxjs/add/operator/map";
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
}
