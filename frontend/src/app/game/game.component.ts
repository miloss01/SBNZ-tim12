import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { GameDTO } from '../model/model';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  gameForm = new FormGroup({
    name: new FormControl(),
    genre: new FormControl(),
    price: new FormControl(),
    releaseDate: new FormControl(),
    singlePlayer: new FormControl(),
    onSale: new FormControl(),
    rating: new FormControl(),
    beta: new FormControl(),
    betaReleaseDate: new FormControl(),
    saleEndDate: new FormControl(),
  })

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
  }

  addGame(): void {
    console.log('Value of form: ' + JSON.stringify(this.gameForm.value))

    let game: GameDTO = {
      name: this.gameForm.value.name,
      genre: this.gameForm.value.genre,
      price: Number(this.gameForm.value.price),
      releaseDate: this.gameForm.value.releaseDate,
      singlePlayer: this.gameForm.value.singlePlayer ? true : false,
      onSale: Number(this.gameForm.value.onSale),
      rating: Number(this.gameForm.value.rating),
      beta: this.gameForm.value.beta ? true : false,
      betaReleaseDate: this.gameForm.value.betaReleaseDate,
      saleEndDate: this.gameForm.value.saleEndDate
    }

    // nije testirano
    this.gameService.postGame(game).subscribe({
      next: (res: GameDTO) => {
        console.log(res)
      },
      error: err => {
        console.log(err)
      }
    })

  }

}
