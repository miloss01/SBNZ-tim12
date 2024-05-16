package com.ftn.sbnz.model.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session implements Serializable {

    private LocalDateTime beginTime;
    // ne znam u kojim jedinicama je vreme
    private Integer duration;
    private Game game;
    private ArrayList<Friend> friends;

}
