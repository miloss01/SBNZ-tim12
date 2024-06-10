import { Component, OnInit } from '@angular/core';
import { AppUserDTO, GameDTO, NotificationDTO } from '../model/model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: AppUserDTO = {
    username: '',
    password: null,
    balance: null,
    favouriteGenres: null,
    playtime: null,
    timezone: null,
    subscriptionType: null,
    games: null,
    wishlist: null,
    friends: null
  };

  notifications: NotificationDTO[] = []

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    // od beka da se povuce user, mozda da se user ceo cuva u localstorage pa da se odatle uzme
    this.user = {
      username: "asd",
      password: "asd",
      balance: 123,
      favouriteGenres: ["ddsf", "dfgdfg"],
      playtime: 22,
      timezone: "+2",
      subscriptionType: "BRONZE",
      games: [],
      wishlist: [],
      friends: []
    }
    // sa beka da se dovuku obavestenja, ovo je samo za testiranje
    let not1: NotificationDTO = {
      name: 'asd',
      description: 'dfgdfgdfg',
      user: this.authService.getLoggedInUser()
    }
    let not2: NotificationDTO = {
      name: 'asd',
      description: 'dfgdfgdfg',
      user: this.authService.getLoggedInUser()
    }
    this.notifications.push(not1)
    this.notifications.push(not2)
  }

}
