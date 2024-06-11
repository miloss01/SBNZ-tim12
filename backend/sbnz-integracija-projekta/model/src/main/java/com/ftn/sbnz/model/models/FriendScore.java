package com.ftn.sbnz.model.models;

import java.io.Serializable;

public class FriendScore implements Serializable {

    // mozda score treba i ovde
    private Double score;
    private AppUser user;
    private AppUser friendRecommendation;

    public FriendScore(Double score, AppUser user, AppUser friendRecommendation) {
        this.score = score;
        this.user = user;
        this.friendRecommendation = friendRecommendation;
    }

    public FriendScore() {

    }

    public Double updateScore(Double value){
        this.score += value;
        return this.score;
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

    public AppUser getFriendRecommendation() {
        return friendRecommendation;
    }

    public void setFriendRecommendation(AppUser friendRecommendation) {
        this.friendRecommendation = friendRecommendation;
    }
}
