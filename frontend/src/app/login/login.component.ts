import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

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

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  login(): void {
    console.log('Value of form: ' + JSON.stringify(this.loginForm.value))

    if (!this.loginForm.valid) {
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
