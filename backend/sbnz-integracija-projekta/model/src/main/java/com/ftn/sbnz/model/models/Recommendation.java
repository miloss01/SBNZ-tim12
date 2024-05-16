package com.ftn.sbnz.model.models;


import com.ftn.sbnz.model.models.enums.RecommendationType;

import java.io.Serializable;

public class Recommendation implements Serializable {

    private RecommendationType type;
    private String description;
    private AppUser user;
    private Game game;

    public Recommendation(RecommendationType type, String description, AppUser user, Game game) {
        this.type = type;
        this.description = description;
        this.user = user;
        this.game = game;
    }

    public Recommendation() {
    }

    public RecommendationType getType() {
        return type;
    }

    public void setType(RecommendationType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
