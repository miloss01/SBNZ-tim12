package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.SubscriptionType;

import java.io.Serializable;
import java.util.ArrayList;

public class AppUser implements Serializable {

    private String username;
    private String password;
    private Double balance;
    private ArrayList<String> favouriteGenres;
    private Double playtime;
    private String timezone;
    private SubscriptionType subscriptionType;
    private ArrayList<Game> games;
    private ArrayList<Game> wishlist;
    private ArrayList<Friend> friends = new ArrayList<>();
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public ArrayList<String> getFavouriteGenres() {
        return favouriteGenres;
    }
    public void setFavouriteGenres(ArrayList<String> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }
    public Double getPlaytime() {
        return playtime;
    }
    public void setPlaytime(Double playtime) {
        this.playtime = playtime;
    }
    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }
    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
    public ArrayList<Game> getGames() {
        return games;
    }
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
    public ArrayList<Game> getWishlist() {
        return wishlist;
    }
    public void setWishlist(ArrayList<Game> wishlist) {
        this.wishlist = wishlist;
    }
    public ArrayList<Friend> getFriends() {
        return friends;
    }
    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

}
