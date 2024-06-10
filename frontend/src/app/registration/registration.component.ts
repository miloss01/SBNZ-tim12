import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppUserDTO } from '../model/model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerForm = new FormGroup({
    // name: new FormControl(),
    // lastname: new FormControl(),
    username: new FormControl(),
    password: new FormControl(),
    favoriteGenres: new FormControl(),
    timezone: new FormControl(),
  })

  errorMessage: string = ''

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
  }

  register(): void {
    console.log('Value of form: ' + JSON.stringify(this.registerForm.value))

    if (!this.registerForm.valid) {
      this.errorMessage = "Form is not valid."
      return
    }

    let favouriteGenres: string[] = String(this.registerForm.value.favoriteGenres).split(",")

    let user: AppUserDTO = {
      username: this.registerForm.value.username,
      password: this.registerForm.value.password,
      balance: null,
      favouriteGenres: favouriteGenres,
      playtime: null,
      timezone: this.registerForm.value.timezone,
      subscriptionType: null,
      games: null,
      wishlist: null,
      friends: null
    }

    this.authService.register(user).subscribe({
      next: (res: AppUserDTO) => {
        console.log(res)
        this.router.navigate(["/login"])
      },
      error: (err) => {
        console.log(err)
        this.errorMessage = "Bad registration"
      }
    })
  }

}
