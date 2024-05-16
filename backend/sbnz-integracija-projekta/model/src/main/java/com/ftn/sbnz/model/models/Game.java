package com.ftn.sbnz.model.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Game implements Serializable {

    private String name;
    private String genre;
    private Double price;
    private LocalDateTime releaseDate;
    private Boolean singlePlayer;
    private Double onSale;
    private Double rating;
    private Boolean beta;
    private LocalDateTime betaReleaseDate;
    private LocalDateTime saleEndDate;

}
