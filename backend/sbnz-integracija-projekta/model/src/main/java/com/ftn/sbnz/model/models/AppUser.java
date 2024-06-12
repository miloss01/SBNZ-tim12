package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.models.enums.SubscriptionType;
import org.kie.api.definition.type.PropertyReactive;

import java.io.Serializable;
import java.util.ArrayList;

@PropertyReactive
public class AppUser implements Serializable {

    private String username;
    private String password;
    private Double balance;
    private ArrayList<String> favouriteGenres;
    private Double playtime;
    private String timezone;
    private SubscriptionType subscriptionType;
    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<Game> wishlist = new ArrayList<>();
    private ArrayList<AppUser> friends = new ArrayList<>();

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

    public ArrayList<AppUser> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<AppUser> friends) {
        this.friends = friends;
    }

    public AppUser(String username, Double balance, ArrayList<Game> games) {
        this.username = username;
        this.balance = balance;
        this.games = games;
    }

    public AppUser() {
    }


}
