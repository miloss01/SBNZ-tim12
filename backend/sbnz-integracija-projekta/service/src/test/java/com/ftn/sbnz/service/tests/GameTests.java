package com.ftn.sbnz.service.tests;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.ftn.sbnz.model.models.*;
import com.ftn.sbnz.model.models.enums.SubscriptionType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class GameTests {

    @Test
    public void testAkoJeIgraIzabranogZanraPovecajScore() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("gameKsession");
        SessionPseudoClock clock = ksession.getSessionClock();


        AppUser user = new AppUser();
        ArrayList<String> favouriteZenres = new ArrayList<>();
        favouriteZenres.add("MMORPG");
        user.setFavouriteGenres(favouriteZenres);
        user.setUsername("123");
        user.setBalance(25d);
//        user.setSubscriptionType(SubscriptionType.GOLD);

        Game game1 = new Game("game1", "MMORPG", 10d, LocalDateTime.now().minusMonths(3), true, 5d, 8d, true, LocalDateTime.now().minusMonths(3), LocalDateTime.now().plusMonths(3));
        Game game2 = new Game("game2", "FPS", 5d, LocalDateTime.now().minusMonths(16), false, 10d, 8d, false, null, LocalDateTime.now().minusMonths(3));
        Game game3 = new Game("game3", "RPG", 30d, LocalDateTime.now().minusMonths(7), false, 20d, 4d, false, null, null);
        GameScore gameScore1 = new GameScore();
        GameScore gameScore2 = new GameScore();
        GameScore gameScore3 = new GameScore();

        gameScore1.setGame(game1);
        gameScore1.setUser(user);
        gameScore1.setScore(0d);

        gameScore2.setGame(game2);
        gameScore2.setUser(user);
        gameScore2.setScore(0d);

        gameScore3.setGame(game3);
        gameScore3.setUser(user);
        gameScore3.setScore(0d);


        for(int i = 0; i < 10; i++){
            Session session = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 10, game1, new ArrayList<>(), user);
            ksession.insert(session);
        }

        clock.advanceTime(8, TimeUnit.DAYS);

        for(int i = 0; i < 10; i++){
            Session session = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 5, game2, new ArrayList<>(), user);
            ksession.insert(session);
        }

        AppUser friend = new AppUser("BestoFrendo", 100d, new ArrayList<>(List.of(game1, game2)));
        friend.setFavouriteGenres(new ArrayList<>());
        user.getFriends().add(friend);
        Purchase purchase = new Purchase(game1, user, LocalDateTime.now(), 30d);
        Purchase purchase1 = new Purchase(game2, user, LocalDateTime.now(), 30d);

        user.getWishlist().add(game1);
        user.getWishlist().add(game2);

        ksession.insert(user);
        ksession.insert(game1);
        ksession.insert(game2);
        ksession.insert(game3);
        ksession.insert(gameScore1);
        ksession.insert(gameScore2);
        ksession.insert(gameScore3);
        ksession.insert(friend);

        ksession.insert(purchase);
        ksession.insert(purchase1);

        int rulesFired = -1;

        System.out.println("Started");
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
    }
}
