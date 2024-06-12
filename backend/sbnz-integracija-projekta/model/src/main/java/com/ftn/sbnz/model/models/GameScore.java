package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.PropertyReactive;

import java.io.Serializable;

@PropertyReactive
public class GameScore implements Serializable {

    private Double score;
    private AppUser user;
    private Game game;

    public Double increaseScore(Double value){
        this.score += value;
        return score;
    }

    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public AppUser getUser() {
        return user;
    }
    public void setUser(AppUser user) {
        this.user = user;
    }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }

}
