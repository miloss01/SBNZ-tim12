package com.ftn.sbnz.model.models;

import java.io.Serializable;

enum RecommendationType {
    BEST_BUY, YOU_WILL_LIKE, MOST_FRIENDS_LIKE, BEST_FREE_TO_PLAY, BEST_RATED, UNFINISHED
}

public class Recommendation implements Serializable {

    private RecommendationType type;
    private String description;
    private AppUser user;
    private Game game;

}
