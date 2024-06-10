import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppUserDTO, LoginDTO } from '../model/model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  getLoggedInUser(): AppUserDTO {
    return JSON.parse(localStorage.getItem("user")!)
  }

  isLoggedIn(): boolean {
    return localStorage.getItem("user") != null ? true : false
  }

  logout() {
    localStorage.removeItem("user")
  }

  login(loginDTO: LoginDTO): Observable<AppUserDTO> {
    return this.http.post<AppUserDTO>(environment.apiHost + "login", loginDTO);
  }

  register(userDTO: AppUserDTO): Observable<AppUserDTO> {
    return this.http.post<AppUserDTO>(environment.apiHost + "register", userDTO);
  }
}
