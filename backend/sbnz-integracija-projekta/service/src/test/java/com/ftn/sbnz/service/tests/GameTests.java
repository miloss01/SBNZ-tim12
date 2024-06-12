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
        user.setSubscriptionType(SubscriptionType.GOLD);
        user.setTimezone("+2");

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

        AppUser friend = new AppUser("BestoFrendo", 100d, new ArrayList<>(List.of(game1, game2)));
        friend.setFavouriteGenres(new ArrayList<>());
        ArrayList favGenres = new ArrayList();
        favGenres.add("asd");
        favGenres.add("neki drugi");
        friend.setFavouriteGenres(favGenres);
        friend.setTimezone("+5");

        user.getFriends().add(friend);

        FriendScore fs1 = new FriendScore(0d, user, friend);
        FriendScore fs2 = new FriendScore(0d, friend, user);

        for(int i = 0; i < 10; i++){
            Session session = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 10, game1, new ArrayList<>(), user);
            Session session1 = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 15, game1, new ArrayList<>(), friend);
            ksession.insert(session);
            ksession.insert(session1);
        }

        clock.advanceTime(8, TimeUnit.DAYS);

        for(int i = 0; i < 10; i++){
            Session session = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 5, game2, new ArrayList<>(), user);
            Session session1 = new Session(LocalDateTime.ofInstant(Instant.ofEpochMilli(clock.getCurrentTime()), ZoneId.systemDefault()), 5, game2, new ArrayList<>(), friend);
            ksession.insert(session);
            ksession.insert(session1);
        }
//        friend.getFriends().add(user);
        Purchase purchase = new Purchase(game1, user, LocalDateTime.now(), 30d);
        Purchase purchase1 = new Purchase(game2, user, LocalDateTime.now(), 30d);
        Purchase purchase2 = new Purchase(game2, user, LocalDateTime.now().minusDays(1), 30d);
        Purchase purchase3 = new Purchase(game2, user, LocalDateTime.now().minusDays(1), 30d);
        Purchase purchase4 = new Purchase(game2, user, LocalDateTime.now().minusDays(1), 30d);

        user.getWishlist().add(game1);
        user.getWishlist().add(game2);

        ksession.insert(fs1);
        ksession.insert(fs2);


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
        ksession.insert(purchase2);
        ksession.insert(purchase3);
        ksession.insert(purchase4);

        int rulesFired = -1;

        System.out.println("Started");
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);
    }

    @Test
    public void forwardChaining1Test(){
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("forward1Ksession");
        SessionPseudoClock clock = ksession.getSessionClock();

        AppUser user = new AppUser();
        ArrayList<String> favouriteZenres = new ArrayList<>();
        favouriteZenres.add("MMORPG");
        user.setFavouriteGenres(favouriteZenres);
        user.setUsername("123");
        user.setBalance(25d);
        user.setSubscriptionType(SubscriptionType.GOLD);
        user.setTimezone("+2");


        AppUser user1 = new AppUser();
        ArrayList<String> user1FavouriteGenres = new ArrayList<>();
        user1FavouriteGenres.add("MMORPG");
        user1.setFavouriteGenres(user1FavouriteGenres);
        user1.setUsername("321");
        user1.setBalance(25d);
        user1.setSubscriptionType(SubscriptionType.PLATINUM);
        user1.setTimezone("+2");


        FriendScore fs1 = new FriendScore(0d, user, user1);
        FriendScore fs2 = new FriendScore(0d, user1, user);

        Game game1 = new Game("game1", "MMORPG", 10d, LocalDateTime.now().minusMonths(3), true, 5d, 8d, true, LocalDateTime.now().minusMonths(3), LocalDateTime.now().plusMonths(3));
        Game game2 = new Game("game2", "FPS", 5d, LocalDateTime.now().minusMonths(16), false, 10d, 8d, false, null, LocalDateTime.now().minusHours(5));
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

        ksession.insert(user);
        ksession.insert(user1);
        ksession.insert(game1);
        ksession.insert(game2);
        ksession.insert(game3);
        ksession.insert(gameScore1);
        ksession.insert(gameScore2);
        ksession.insert(gameScore3);

        ksession.insert(fs1);
        ksession.insert(fs2);

        int rulesFired = -1;


        System.out.println("Started");
        rulesFired = ksession.fireAllRules();
        System.out.println(rulesFired);

        String str = "adsasdsada";
        str.startsWith("asdas");
    }


    @Test
    public void testZaOdbranu(){

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("gameKsession");
        SessionPseudoClock clock = ksession.getSessionClock();

        
    }

}
