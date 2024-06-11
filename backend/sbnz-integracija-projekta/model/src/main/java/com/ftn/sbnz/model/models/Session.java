package com.ftn.sbnz.model.models;

import jdk.jfr.Timestamp;
import org.kie.api.definition.type.PropertyReactive;
import org.kie.api.definition.type.Role;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@PropertyReactive
@Role(Role.Type.EVENT)
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDateTime beginTime;
    // ne znam u kojim jedinicama je vreme
    private Integer duration;
    private Game game;
    private ArrayList<AppUser> friends;

    private AppUser user;

    public Session() {
        super();
    }

    public Session(LocalDateTime beginTime, Integer duration, Game game, ArrayList<AppUser> friends, AppUser user) {
        super();
        this.beginTime = beginTime;
        this.duration = duration;
        this.game = game;
        this.friends = friends;
        this.user = user;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setFriends(ArrayList<AppUser> friends) {
        this.friends = friends;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<AppUser> getFriends() {
        return friends;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
