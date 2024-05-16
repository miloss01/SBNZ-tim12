package com.ftn.sbnz.model.models;

import java.io.Serializable;
import java.util.ArrayList;

enum SubscriptionType {
    BRONZE, SILVER, GOLD, PLATINUM
}

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
    private ArrayList<Friend> friends;

}
