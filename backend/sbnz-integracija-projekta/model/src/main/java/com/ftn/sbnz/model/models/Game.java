package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.PropertyReactive;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@PropertyReactive
public class Game implements Serializable {

    private String name;
    private String genre;
    private Double price;
    private LocalDateTime releaseDate;

    public Game(String name, String genre, Double price, LocalDateTime releaseDate, Boolean singlePlayer, Double onSale, Double rating, Boolean beta, LocalDateTime betaReleaseDate, LocalDateTime saleEndDate) {
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.releaseDate = releaseDate;
        this.singlePlayer = singlePlayer;
        this.onSale = onSale;
        this.rating = rating;
        this.beta = beta;
        this.betaReleaseDate = betaReleaseDate;
        this.saleEndDate = saleEndDate;
    }

    public Game(){

    }

    private Boolean singlePlayer;
    private Double onSale = 0d;
    private Double rating;
    private Boolean beta;
    private LocalDateTime betaReleaseDate;
    private LocalDateTime saleEndDate;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Boolean getSinglePlayer() {
        return singlePlayer;
    }
    public void setSinglePlayer(Boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }
    public Double getOnSale() {
        return onSale;
    }
    public void setOnSale(Double onSale) {
        this.onSale = onSale;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public Boolean getBeta() {
        return beta;
    }
    public void setBeta(Boolean beta) {
        this.beta = beta;
    }
    public LocalDateTime getBetaReleaseDate() {
        return betaReleaseDate;
    }
    public void setBetaReleaseDate(LocalDateTime betaReleaseDate) {
        this.betaReleaseDate = betaReleaseDate;
    }
    public LocalDateTime getSaleEndDate() {
        return saleEndDate;
    }
    public void setSaleEndDate(LocalDateTime saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

}
