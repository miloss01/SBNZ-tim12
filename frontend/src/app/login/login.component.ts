import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { AppUserDTO, LoginDTO } from '../model/model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
  })

  errorMessage: string = ''

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
  }

  login(): void {
    console.log('Value of form: ' + JSON.stringify(this.loginForm.value))

    if (!this.loginForm.valid) {
      this.errorMessage = "Form is not valid."
      return
    }

    let loginDTO: LoginDTO = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    }

    // za testiranje
    let u: AppUserDTO = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password,
      balance: null,
      favouriteGenres: null,
      playtime: null,
      timezone: null,
      subscriptionType: null,
      games: null,
      wishlist: null,
      friends: null
    }
    localStorage.setItem("user", JSON.stringify(u))
    this.router.navigate(["/overview"])

    // nije testirano
    // this.authService.login(loginDTO).subscribe({
    //   next: (res: AppUserDTO) => {
    //     console.log(res)
    //     localStorage.setItem("user", JSON.stringify(res))
    //     this.router.navigate(["/overview"])
    //   },
    //   error: (err) => {
    //     console.log(err)
    //     this.errorMessage = "Bad login"
    //   }
    // })
  }

}
