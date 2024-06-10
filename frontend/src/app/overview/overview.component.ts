import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AppUserDTO, GameDTO, SessionDTO } from '../model/model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css']
})
export class OverviewComponent implements OnInit {

  sessionForm = new FormGroup({
    beginTime: new FormControl(),
    duration: new FormControl(),
    game: new FormControl(),
    friends: new FormControl(),
  })

  bestBuy: string = ""
  youWillLike: string = ""
  mostFriendsPlay: string = ""
  bestFreeToPlay: string = ""
  bestRated: string = ""
  unfinished: string = ""
  
  recommendedFriends: string = ""

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    let user: AppUserDTO = this.authService.getLoggedInUser()
    console.log(user)
  }

  addSession() {
    console.log('Value of form: ' + JSON.stringify(this.sessionForm.value))

    let usernames: string[] = String(this.sessionForm.value.friends).split(",")
    let friends: AppUserDTO[] = []

    for (const username of usernames) {
      // odraditi trim
      // sa beka da povuce korisnike po username, a ne ovaj dummy
      let friend: AppUserDTO = {
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
      }
      friends.push(friend)
    }

    // sa beka da povuce game, a ne ovaj dummy
    let game: GameDTO = {
      name: '',
      genre: '',
      price: 0,
      releaseDate: '',
      singlePlayer: false,
      onSale: 0,
      rating: 0,
      beta: false,
      betaReleaseDate: '',
      saleEndDate: ''
    }

    let session: SessionDTO = {
      beginTime: this.sessionForm.value.beginTime,
      duration: Number(this.sessionForm.value.duration),
      game: game,
      friends: friends
    }
    console.log(session)
  }

  getRecommendation() {

  }

}
