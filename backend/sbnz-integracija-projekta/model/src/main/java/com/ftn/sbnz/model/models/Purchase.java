package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;
import java.time.LocalDateTime;

@Role(Role.Type.EVENT)
@Expires("10s")
public class Purchase implements Serializable {
    private Game game ;
    private AppUser user;
    private LocalDateTime purchaseDate;
    private Double price;

    public Purchase(Game game, AppUser user, LocalDateTime purchaseDate, Double price) {
        this.game = game;
        this.user = user;
        this.purchaseDate = purchaseDate;
        this.price = price;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
