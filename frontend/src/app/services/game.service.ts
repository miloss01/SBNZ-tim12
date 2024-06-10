import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GameDTO } from '../model/model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpClient) { }

  getGameByName(gameName: string): Observable<GameDTO> {
    return this.http.get<GameDTO>(environment.apiHost + "game/" + gameName);
  }

  postGame(game: GameDTO): Observable<GameDTO> {
    return this.http.post<GameDTO>(environment.apiHost + "game", game);
  }
}
