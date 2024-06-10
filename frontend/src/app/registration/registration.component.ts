import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

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

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  register(): void {
    console.log('Value of form: ' + JSON.stringify(this.registerForm.value))

    if (!this.registerForm.valid) {
      this.errorMessage = "Form is not valid."
      return
    }

    // let appUserDTO: AppUserDTO = {
    //   id: null,
    //   name: this.registerForm.value.name!,
    //   lastName: this.registerForm.value.lastname!,
    //   email: this.registerForm.value.email!,
    //   password: this.registerForm.value.password!,
    //   active: null
    // }
  }

}
